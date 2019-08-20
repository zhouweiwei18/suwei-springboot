package com.mall.content;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.concurrent.CountDownLatch;

/**
 * @author weiwei
 * @create 2019-08-20 17:09
 */
@MapperScan(value = "com.mall.manager.mapper")
@SpringBootApplication
public class ContentServiceApplicationStarter {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("-------------------------加载内容管理服务-----------------------------");
        new SpringApplicationBuilder()
                .sources(ContentServiceApplicationStarter.class)
                .web(WebApplicationType.NONE)
                .run(args);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        countDownLatch.await();
    }

}
