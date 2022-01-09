package com.zjk.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName:ApiGatewaty
 * @Description:
 * @Author:Zhou Jingkai
 * @Data:2022-01-07 23:16
 * @Version:1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGateway {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateway.class,args);
    }
}
