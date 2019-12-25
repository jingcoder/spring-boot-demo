package com.example.demo.security.Filter;

import com.example.demo.common.MockTestData;
import com.example.demo.security.model.UrlConfigAttribute;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author xu.xiaojing
 * @Date 2018/10/8 10:20
 * @Email xu.xiaojing@frontsurf.com
 * @Description 定义资源的权限、角色
 */
@Component
public class UrlInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static  Map<String,UrlConfigAttribute> resourcePermissions = MockTestData.loadResourcePermission();


    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    // 或者说是  访问当前资源需要哪些 权限，返回所需的权限集合
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        ConfigAttribute attribute = resourcePermissions.get(request.getServletPath());
        LinkedList list = new LinkedList();
        list.add(attribute);
        return list;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    /**
     * 加载资源权限
     */
    @PostConstruct
    public  void loadResourcePermission(){

        UrlInvocationSecurityMetadataSource.resourcePermissions = MockTestData.loadResourcePermission();
    }
}
