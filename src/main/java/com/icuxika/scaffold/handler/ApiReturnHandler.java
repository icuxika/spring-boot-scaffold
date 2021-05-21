package com.icuxika.scaffold.handler;

import com.icuxika.scaffold.annotation.ApiReturn;
import com.icuxika.scaffold.config.ApiData;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.io.IOException;
import java.util.List;

public class ApiReturnHandler extends RequestResponseBodyMethodProcessor {

    public ApiReturnHandler(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return super.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
        ApiReturn apiReturn = returnType.getMethodAnnotation(ApiReturn.class);
        if (apiReturn != null) {
            if (apiReturn.disable()) {
                super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
                return;
            }
        }
        System.out.println("进入自定义返回值处理器");
        ApiData<String> apiData = new ApiData<>();
        apiData.setCode(200);
        apiData.setData("数据");
        apiData.setMsg("信息");
        super.handleReturnValue(apiData, returnType, mavContainer, webRequest);
    }

}
