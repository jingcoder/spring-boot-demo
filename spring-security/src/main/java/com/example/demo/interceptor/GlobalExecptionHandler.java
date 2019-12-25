package com.example.demo.interceptor;

import com.example.demo.common.entity.Return;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author xu.xiaojing
 * @Date 2018/9/28 10:42
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@ControllerAdvice
public class GlobalExecptionHandler {

    @ExceptionHandler()
    @ResponseBody
    public Return defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        Return r;
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            System.out.println(e);
            r= new Return(404,"未找到您访问的资源。");
        } else {
            System.out.println(e);
            r= new Return(500,"服务器资源繁忙,请联系管理员。");
        }
        return r;
    }
}
