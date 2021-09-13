package com.icuxika.scaffold.module.activiti.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.icuxika.scaffold.config.ApiData;
import com.icuxika.scaffold.parameter.PageQuery;
import com.icuxika.scaffold.parameter.QueryPage;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/activiti-explorer/service")
public class ActivitiExplorerController {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiExplorerController.class);

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BPMN20_XML = ".bpmn20.xml";

    @RequestMapping("/create")
    public void createModel(HttpServletRequest request, HttpServletResponse response) {
        try {
            String modelName = "modelName";
            String modelKey = "modelKey";
            String description = "description";

            ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

            RepositoryService repositoryService = processEngine.getRepositoryService();

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.put("stencilset", stencilSetNode);
            // 定义新模型
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, modelName);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(modelName);
            modelData.setKey(modelKey);

            //保存模型
            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes(StandardCharsets.UTF_8));
            response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
        } catch (Exception e) {
            logger.error("新建Activiti模型出错");
        }
    }

    /**
     * 模型转换为bpmn文件
     *
     * @param id 模型id
     */
    @RequestMapping("/generateBpmnFile/{id}")
    public void generateBpmnFile(@PathVariable String id, HttpServletResponse response) {
        try {
            JsonNode editorNode = objectMapper.readTree(repositoryService.getModelEditorSource(id));
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            BpmnModel model = jsonConverter.convertToBpmnModel(editorNode);
            String fileName = model.getMainProcess().getId() + ".bpmn20.xml";
            byte[] bytes = new BpmnXMLConverter().convertToXML(model);
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            try (OutputStream outputStream = response.getOutputStream()) {
                StreamUtils.copy(bytes, outputStream);
            }
        } catch (IOException e) {
            logger.error("生成bpmn文件失败");
        }
    }

    @RequestMapping("/page")
    public QueryPage<List<Model>> pageModel(PageQuery pageQuery) {
        ModelQuery modelQuery = repositoryService.createModelQuery()
                .latestVersion().orderByLastUpdateTime().desc();
        int currentPage = pageQuery.getCurrentPage() == null ? 1 : pageQuery.getCurrentPage().intValue();
        int pageSize = pageQuery.getPageSize() == null ? 15 : pageQuery.getPageSize().intValue();

        QueryPage<List<Model>> page = new QueryPage<>();
        page.setTotal(modelQuery.count());
        List<Model> modelList = modelQuery.listPage((currentPage - 1) * pageSize, pageSize);
        page.setData(modelList);
        page.setCurrentPage((long) currentPage);
        page.setPageSize((long) pageSize);
        return page;
    }

    @RequestMapping("/deleteModel/{id}")
    public ApiData<Void> deleteModel(@PathVariable String id) {
        repositoryService.deleteModel(id);
        return ApiData.ok("删除模型成功");
    }

    @RequestMapping("/deployModel/{id}")
    public void deployModel(@PathVariable String id) {
        try {
            Model model = repositoryService.getModel(id);
            ObjectNode objectNode = (ObjectNode) objectMapper.readTree(repositoryService.getModelEditorSource(model.getId()));
            BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(objectNode);

            String processName = model.getName();
            if (!StringUtils.endsWithIgnoreCase(processName, BPMN20_XML)) {
                processName += BPMN20_XML;
            }
            // 部署流程
            Deployment deployment = repositoryService.createDeployment().name(model.getName())
                    .addBpmnModel(processName, bpmnModel)
                    .deploy();

        } catch (IOException e) {
            logger.error("模型部署失败");
        }
    }
}
