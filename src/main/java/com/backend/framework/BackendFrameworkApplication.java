package com.backend.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.backend.framework.mapper")
public class BackendFrameworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendFrameworkApplication.class, args);
    }
}
