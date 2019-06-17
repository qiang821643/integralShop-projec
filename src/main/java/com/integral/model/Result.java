package com.integral.model;


import com.integral.constant.Constant;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int status=0;

    private String msg;

    private Object data;

    public static Result build(){

        return new Result();
    }

    public static Result ok(Result result,String msg,Object data){
        result.setStatus(Constant.OK_CODE);
        if(!StringUtils.isBlank(msg)) {
            result.setMsg(msg);
        }else {
            result.setMsg(Constant.OK_MESAGE);
        }
        result.setData(data);
        return result;
    }

    public static Result error(Result result,String msg,Object data){

        result.setStatus(Constant.ERROR_CODE);
        if(!StringUtils.isBlank(msg)) {
            result.setMsg(msg);
        }else {
            result.setMsg(Constant.ERROR_MSAGE);
        }
        result.setData(data);
        return result;
    }
}
