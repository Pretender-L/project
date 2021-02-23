package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * EnableCaching:开启缓存功能
 */
@SpringBootApplication
@EnableCaching
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class);
    }

}
