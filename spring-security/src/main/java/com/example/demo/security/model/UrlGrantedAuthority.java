package com.example.demo.security.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @Author xu.xiaojing
 * @Date 2018/10/8 0:11
 * @Email xu.xiaojing@frontsurf.com
 * @Description 自定义的权限类，精确到方法
 */

public class UrlGrantedAuthority implements GrantedAuthority {

    private String permissionUrl;
    private String method;

    public UrlGrantedAuthority(String permissionUrl, String method) {
        this.permissionUrl = permissionUrl;
        this.method = method;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getAuthority() {
        return this.permissionUrl + ";" + this.method;
    }
}
