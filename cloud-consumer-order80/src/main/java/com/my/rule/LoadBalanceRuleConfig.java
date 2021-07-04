package com.my.rule;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBalanceRuleConfig {
    /**
     * 自定义负载均衡策略
     * @return
     */
    @Bean
    public IRule mySelfRule(){
        return new MyRoundRobinRule();
    }
}
