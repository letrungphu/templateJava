package com.template.template;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.template.template.auth.mapper")
//@MapperScan({"com.template.template.auth.mapper", "com.template.template.api.oracle.mapper", "com.template.template.api.mysql.mapper"})
public class TemplateApplication {
    public static void main(String[] args) {
        SpringApplication.run(TemplateApplication.class, args);
    }

}
