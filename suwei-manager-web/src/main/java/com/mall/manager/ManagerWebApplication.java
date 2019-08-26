package com.mall.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author weiwei
 * @create 2019-08-10 13:48
 */
@SpringBootApplication
public class ManagerWebApplication {

    public static void main(String[] args) {
        System.out.println("-------------------------后台管理-----------------------------");
        SpringApplication.run(ManagerWebApplication.class, args);
    }

}
