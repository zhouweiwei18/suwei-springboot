package com.mall.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author weiwei
 * @create 2019-08-20 10:26
 */
@SpringBootApplication
public class PortalWebApplicationStarter {
    public static void main(String[] args) {
        System.out.println("-------------------------前台页面启动-----------------------------");
        SpringApplication.run(PortalWebApplicationStarter.class, args);
    }
}