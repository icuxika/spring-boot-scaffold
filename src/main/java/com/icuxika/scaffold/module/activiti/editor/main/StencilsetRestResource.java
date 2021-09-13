package com.icuxika.scaffold.module.activiti.editor.main;

import com.icuxika.scaffold.annotation.ApiReturn;
import org.activiti.engine.ActivitiException;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Tijs Rademakers
 */
@RestController
@RequestMapping("/activiti-explorer/service")
public class StencilsetRestResource {

  @ApiReturn(disable = true)
  @RequestMapping(value = "/editor/stencilset", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
  public @ResponseBody
  String getStencilset() {
    InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("static/stencilset.json");
    try {
      return IOUtils.toString(stencilsetStream, StandardCharsets.UTF_8);
    } catch (Exception e) {
      throw new ActivitiException("Error while loading stencil set", e);
    }
  }
}
