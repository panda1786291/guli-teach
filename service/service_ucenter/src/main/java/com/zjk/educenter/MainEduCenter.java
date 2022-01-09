package com.zjk.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName:MainEduCenter
 * @Description:
 * @Author:Zhou Jingkai
 * @Data:2021-12-30 22:56
 * @Version:1.0
 **/

@SpringBootApplication
@ComponentScan(basePackages = {"com.zjk"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.zjk.educenter.mapper")
public class MainEduCenter {
    public static void main(String[] args) {
        SpringApplication.run(MainEduCenter.class,args);
    }
}
