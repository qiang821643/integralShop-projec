package com.integral.exception;

import java.io.Serializable;

/**
 * @author: create QiangShW
 * @version: v1.0
 * @description: com.integral.exception
 * @date:2019/5/31
 **/
public class UnFillStatceTraceException extends RuntimeException implements Serializable {


    public UnFillStatceTraceException() {
        this(null, null);
    }

    public UnFillStatceTraceException(String msg) {
        this(msg, null);
    }

    public UnFillStatceTraceException(Throwable cause) {
        this(null, cause);
    }

    public UnFillStatceTraceException(String message, Throwable cause) {
        super(message, cause, false, false);
    }

}
