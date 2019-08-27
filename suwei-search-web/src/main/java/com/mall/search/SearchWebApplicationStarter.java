package com.mall.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchWebApplicationStarter {
    public static void main(String[] args) {
        System.out.println("---------------启动前端搜索页面---------------");
        SpringApplication.run(SearchWebApplicationStarter.class, args);
    }
}
