package com.example.demo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Author xu.xiaojing
 * @Date 2018/12/28 16:46
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 */

public class ServletInitializer extends SpringBootServletInitializer {

//    private Logger logger = LoggerFactory.getLogger(ServletInitializer.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

//        logger.info("starting spring boot initializer ......");

        return application.sources(DemoApplication.class);
    }

}
