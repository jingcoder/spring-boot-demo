package com.example.springmybatismapper4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.example.springmybatismapper4.mapper")
public class SpringMybatisMapper4Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringMybatisMapper4Application.class, args);
    }

}

