package com.icuxika.scaffold.module.activiti.controller;

import com.icuxika.scaffold.module.activiti.entity.SmallTask;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("activiti")
public class ActivitiController {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiController.class);

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @RequestMapping("info")
    public void info() {
        logger.info("Process Number: " + repositoryService.createProcessDefinitionQuery().count());
        logger.info("Task Number: " + taskService.createTaskQuery().count());
        repositoryService.createProcessDefinitionQuery().list().forEach(processDefinition -> {
            System.out.println(processDefinition.getId());
            System.out.println(processDefinition.getName());
        });

    }

    @RequestMapping("startSimple")
    public void startSimple() {

        SmallTask smallTask = new SmallTask(2, 3);

        Map<String, Object> map = new HashMap<>();
        map.put("task", smallTask);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Simple", map);
    }

    @RequestMapping("viewSimple")
    public void viewSimple() {
        taskService.createTaskQuery().list().forEach(task -> {
            System.out.println(task.getId());
            System.out.println(task.getName());
            System.out.println(task.getAssignee());
        });
    }

    @GetMapping("completeSimple/{taskId}")
    public void completeSimple(@PathVariable("taskId") String taskId) {
        taskService.complete(taskId);
    }

    @RequestMapping("recordSimple")
    public void recordSimple() {
        historyService.createHistoricTaskInstanceQuery().list().forEach(historicTaskInstance -> System.out.println(historicTaskInstance));
    }
}
