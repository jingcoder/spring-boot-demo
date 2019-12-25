package com.example.demo.security.monitor;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author xu.xiaojing
 * @Date 2018/10/10 8:42
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@Component
public class CustomAuthenticationSuccesstHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //super.onAuthenticationSuccess(request, response, authentication);

        super.getRedirectStrategy().sendRedirect(request, response, "/login/success");
        super.clearAuthenticationAttributes(request);
    }
}
