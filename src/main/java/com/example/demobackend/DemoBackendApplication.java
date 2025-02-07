package com.example.demobackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demobackend.mapper")
public class DemoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoBackendApplication.class, args);
    }

}
