package com.example.hitsoftware;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.hitsoftware.mapper")
public class HitSoftwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(HitSoftwareApplication.class, args);
    }

}
