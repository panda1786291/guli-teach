package com.zjk.canal;

import com.zjk.canal.client.CanalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName:CanalApplication
 * @Description:
 * @Author:Zhou Jingkai
 * @Data:2022-01-06 22:53
 * @Version:1.0
 **/
@SpringBootApplication
public class CanalApplication implements CommandLineRunner {
    @Autowired
    private CanalClient canalClient;

    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        canalClient.run();
    }
}
