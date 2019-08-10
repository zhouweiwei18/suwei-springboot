package com.mall.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.concurrent.CountDownLatch;

/**
 * @author weiwei
 * @create 2019-08-10 14:22
 */
@MapperScan(value = "com.mall.manager.mapper")
@SpringBootApplication
public class ManagerServiceApplication {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("-------------------------加载服务-----------------------------");

        new SpringApplicationBuilder()
                .sources(ManagerServiceApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
    }
}
