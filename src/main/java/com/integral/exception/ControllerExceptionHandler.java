package com.integral.exception;

import com.integral.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.exception
 * @date:2019/5/31
 **/
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {


    @ExceptionHandler(APIException.class)
    public ResponseEntity<Result> handleBusinessException(APIException apiException){
        return ResponseEntity.status(apiException.getStatusCode().getHttpStatusCode())
                .body(Result.error(Result.build(),apiException.getMessage(),""));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleUnknownException(Exception e){
        log.error("服务器未知异常",e);
        return Result.error(Result.build(),"服务器未知异常","");
    }

}
