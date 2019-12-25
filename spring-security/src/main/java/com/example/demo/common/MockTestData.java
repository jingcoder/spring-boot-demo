package com.example.demo.common;

import com.example.demo.security.UrlUserDetailsService;
import com.example.demo.security.model.UrlConfigAttribute;
import com.example.demo.security.model.UrlGrantedAuthority;
import com.example.demo.security.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

/**
 * @Author xu.xiaojing
 * @Date 2018/10/7 18:18
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class MockTestData {


    public static UserDetails loadUser(String s) {

        if(s.equals("admin")){
            User user = new User();
            user.setUsername("admin");
            BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
            // 模拟从数据库取出来的数据，所以是加密后的密码
            user.setPassword(encoder.encode("qwert"));
            user.setId("123456");
            Set<String> permissons = new HashSet<>();
            permissons.add("/demo/dosomething");
            permissons.add("/admin/dba");
            user.setPermissons(permissons);
         // 设置权限
            List<UrlGrantedAuthority> list = new LinkedList<>();
            list.add(new UrlGrantedAuthority("/demo/something","getSomething"));
            list.add(new UrlGrantedAuthority("/admin/dba","adminAndDba"));
            list.add(new UrlGrantedAuthority("/admin/bbb","bbb"));
            user.setAuthorities(list);
            return user;
        }
        return null;
    }

    public static Map<String,UrlConfigAttribute> loadResourcePermission() {

        Map<String,UrlConfigAttribute>  resourcePermissions = new HashMap<>();
        UrlConfigAttribute configAttribute;
        configAttribute = new UrlConfigAttribute("/demo/something","getSomething");
        resourcePermissions.put("/demo/something",configAttribute);

        configAttribute = new UrlConfigAttribute("/admin/dba","adminAndDba");
        resourcePermissions.put("/admin/dba",configAttribute);

        configAttribute = new UrlConfigAttribute("/admin/bbb","bbb");
        resourcePermissions.put("/admin/bbb",configAttribute);

        return resourcePermissions;
    }
}
