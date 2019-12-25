package com.example.demo.security.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author xu.xiaojing
 * @Date 2018/10/8 9:49
 * @Email xu.xiaojing@frontsurf.com
 * @Description   自定义权限过滤器,资源管理拦截器
 */
@Component
public class UrlFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private UrlInvocationSecurityMetadataSource securityMetadataSource;  // 资源服务器

    @Autowired
    public void setMyAccessDecisionManager(UrlAccessDecisionManager urlAccessDecisionManager) { // 权限校验服务器
        super.setAccessDecisionManager(urlAccessDecisionManager);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        invoke(fi);
    }

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
       //fi里面有一个被拦截的url
       //里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
       //再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
         InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
       //执行下一个拦截器
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }


    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {

        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {

        return this.securityMetadataSource;
    }
}
