package com.icuxika.scaffold.config;

import com.icuxika.scaffold.handler.ApiReturnHandler;
import com.icuxika.scaffold.resolver.JsonClipResolver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer, InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new JsonClipResolver());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        if (returnValueHandlers != null) {
            List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(returnValueHandlers);
            handlers.add(0, new ApiReturnHandler(requestMappingHandlerAdapter.getMessageConverters()));
            requestMappingHandlerAdapter.setReturnValueHandlers(handlers);
        }
    }
}
