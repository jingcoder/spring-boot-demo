package com.example.demo.messagebustest;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 返回数据包装
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Return implements Serializable {

    private static final long serialVersionUID = -5515899356745165159L;

    private Object data;
    private Integer code;
    private String message;

    public Return() {

    }

    public Return(Integer code) {
        this.code = code;
    }

    public Return(Object data, Integer code) {
        this.data = data;
        this.code = code;
    }

    public Return(Object data, Integer code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public Return(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Return success(Object data) {
        return new Return(data, 200);
    }

    public static Return success(Object data,String message) {
        return new Return(data, 200,message);
    }

    public static Return success() {
        return new Return(200);
    }

    public static Return fail(Integer code, String msg) {
        return new Return(code, msg);
    }

    public static Return fail(String msg) {
        return new Return(400, msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
        setDefaultMsg();
    }

    private void setDefaultMsg() {
        if (HttpStatus.FORBIDDEN.value() == this.code) {
            this.code = 200;
            this.message = "权限不足";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toCustomString(){
        StringBuilder builder = new StringBuilder();
        if(data != null && data instanceof String){
            builder.append("{");
            builder.append("\"data\": " + data);
            builder.append(",");
            builder.append("\"code\":" + code);
            builder.append(",");
            builder.append("\"message\":\"" + message + "\"");
            builder.append("}");
        }
        return builder.toString();
    }
}
