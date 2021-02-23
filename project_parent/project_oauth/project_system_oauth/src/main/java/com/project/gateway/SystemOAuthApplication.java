package com.project.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.project.system.feign"})
public class SystemOAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemOAuthApplication.class, args);
    }
}
