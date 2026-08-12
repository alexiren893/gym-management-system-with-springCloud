package com.gym.employee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@MapperScan("com.gym.employee.mapper")
public class employeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(employeeApplication.class, args);

    }
}

