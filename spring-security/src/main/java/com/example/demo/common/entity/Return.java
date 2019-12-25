package com.example.demo.common.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/2 22:30
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Return implements Serializable {

    private static final long serialVersionUID = -5515899356745165159L;

    private Integer code;
    private Object data;
    private String message;

    public Return(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public Return(Integer code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Return(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Return success(String message){
        return new Return(200,message);
    }

    public static Return success(Object data){
        return new Return(200,data);
    }

    public static  Return success(Object data,String message){
        return new Return(200,data,message);
    }

    public static Return fail(String message){
        return new Return(400,message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
