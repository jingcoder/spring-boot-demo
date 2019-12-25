package com.example.demo.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @Author xu.xiaojing
 * @Date 2018/10/7 18:17
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class User implements UserDetails {

    private String username;
    private String password;
    private String id;
    private Set<String> permissons;

    private List<? extends GrantedAuthority> authorities;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getPermissons() {
        return permissons;
    }

    public void setPermissons(Set<String> permissons) {
        this.permissons = permissons;
    }

    public void setAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    // 下面的所有属性必须 返回 true

    @Override
    public boolean isAccountNonExpired() {
        return true;
    } // 账号是否可用

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }  // 用户没有被锁定

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    } // 证书是否可用

    @Override
    public boolean isEnabled() {
        return true;
    }  //
}
