package com.example.demo.security.Filter;

import com.example.demo.security.model.UrlGrantedAuthority;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @Author xu.xiaojing
 * @Date 2018/10/8 9:57
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {



    // decide 方法是判定是否拥有权限的决策方法，
    //authentication 是释CustomUserService中循环添加到 GrantedAuthority 对象中的权限信息集合.
    //object 包含客户端发起的请求的requset信息，可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
    //configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法返回的结果，此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollectionUtils.isEmpty(configAttributes)) {
            throw new AccessDeniedException("not allow");
        }

        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        if (matchers("/oauth/**", request) || matchers("/login/*",request)) {
            return;
        }

        String url, method;

        // 资源权限集合
        for(ConfigAttribute ca : configAttributes){
            // 用户的权限集合
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if(authentication.getPrincipal().equals("anonymousUser")){
                    return;
                }
                UrlGrantedAuthority cga = (UrlGrantedAuthority) ga;
                url = cga.getPermissionUrl();
                if(url == null || url.equals("")){
                    continue;
                }
                method = cga.getMethod();
                // 判断路径权限
                if (matchers(url, request)) {
                    // 判断方法是否正确
                   String method1 = request.getMethod();
                    if (request.getMethod().equals(method) || "ALL".equals(method)) {
                        return;
                    }
                }
            }
        }

        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    private boolean matchers(String url, HttpServletRequest request) {
        AntPathRequestMatcher matcher = new AntPathRequestMatcher(url);
        if (matcher.matches(request)) {
            return true;
        }
        return false;
    }
}
