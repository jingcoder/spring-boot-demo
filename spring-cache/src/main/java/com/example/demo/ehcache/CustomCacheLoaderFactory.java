package com.example.demo.ehcache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.bootstrap.BootstrapCacheLoader;
import net.sf.ehcache.bootstrap.BootstrapCacheLoaderFactory;

import java.util.Properties;

/**
 * @Author xu.xiaojing
 * @Date 2018/11/28 19:05
 * @Email xu.xiaojing@frontsurf.com
 * @Description   只能定义初始化，缓存初始化
 */

public class CustomCacheLoaderFactory extends BootstrapCacheLoaderFactory {
    @Override
    public BootstrapCacheLoader createBootstrapCacheLoader(Properties properties) {
        return new BootstrapCacheLoader() {
            @Override
            public void load(Ehcache ehcache) throws CacheException {
            }

            @Override
            public boolean isAsynchronous() {
                return false;
            }

            @Override
            public Object clone() throws CloneNotSupportedException {
                return null;
            }
        };
    }
}
