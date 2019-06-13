package com.integral.exception;


import org.apache.commons.lang3.StringUtils;
import com.integral.exception.UnFillStatceTraceException;
/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.exception
 * @date:2019/5/31
 **/
public class APIException extends UnFillStatceTraceException {
    private static final long serialVersionUID = -1043498038361659805L;

    private final StatusCode statusCode;

    public APIException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public APIException(StatusCode statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }

    @Override
    public String getMessage() {
        return StringUtils.defaultIfBlank(super.getMessage(), statusCode.defaultMessage);
    }
}
