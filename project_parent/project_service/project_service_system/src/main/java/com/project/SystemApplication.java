package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.project.system.dao"})
/*
@EnableGlobalMethodSecurity(prePostEnabled = true)
*/
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class);
    }

}
