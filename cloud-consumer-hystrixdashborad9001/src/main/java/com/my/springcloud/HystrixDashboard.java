package com.my.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author linzhengtao
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboard {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboard.class,args);
    }

}
