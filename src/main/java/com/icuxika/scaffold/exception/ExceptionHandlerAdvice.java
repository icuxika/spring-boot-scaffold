package com.icuxika.scaffold.exception;

import com.icuxika.scaffold.config.ApiData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiData<Void> handleException(Exception e) {
        ApiData<Void> apiData = new ApiData<>();
        apiData.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiData.setMsg(e.getMessage());
        return apiData;
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ApiData<Void> handleException(ServiceException e) {
        ApiData<Void> apiData = new ApiData<>();
        apiData.setCode(e.getStatusCode());
        apiData.setMsg(e.getMessage());
        return apiData;
    }
}
