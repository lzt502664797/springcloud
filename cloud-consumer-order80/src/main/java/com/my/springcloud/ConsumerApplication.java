package com.my.springcloud;

import com.my.rule.LoadBalanceRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
/**
 *  设置自定义负载均衡算法
 */
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = LoadBalanceRuleConfig.class)
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
    }
}
