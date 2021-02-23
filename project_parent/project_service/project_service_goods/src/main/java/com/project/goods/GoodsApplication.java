package com.project.goods;

import com.project.util.IdWorker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.project.goods.dao"})
public class GoodsApplication {

    @Value("${workId}")
    private int workId;

    @Value("${datacenterId}")
    private int datacenterId;

    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(workId, datacenterId);
    }

}
