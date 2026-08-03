package com.gym;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@MapperScan("com.gym.mapper")
public class equipmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(equipmentApplication.class, args);
    }
}

