package com.my.springcloud.controller;

import cn.hutool.core.lang.UUID;
import com.my.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author linzhengtao
 */
@RestController
@DefaultProperties(defaultFallback = "paymentInfo_GlobalHandler")
@Slf4j
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_Ok(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_Ok(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand
    /**
     * HystrixCommand 默认超时时长1s
     * 在未执行到 paymentHystrixService 前出现异常将使用 HystrixCommand 的降级方法
     * 若 paymentHystrixService 出现异常，且paymentHystrixService已经定义好降级方法，
     * 则使用 paymentHystrixService 中的降级方法。
     */
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
//        int i = 10/0;
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            log.warn("time out");
        }
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }
    private String paymentInfo_TimeOutHandler(Integer id){
        return "80order 超时1.5s 无法处理 请稍后再试";
    }
    private String paymentInfo_GlobalHandler(){
        return "80order 出现异常 无法处理 请稍后再试";
    }

    @GetMapping("/consumer/payment/circuit/{id}")
    @HystrixCommand(fallbackMethod = "idException",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value="true"),
            // 十秒钟内熔断，十秒钟后尝试服务调用
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value="10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value="10")
    })
    public String circuit(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException();
        }

        String s = UUID.randomUUID().toString();
        return "id : " + id + ",流水号：" + s;
    }
    private String idException(Integer id){
        return "id: " + id + "为负数。";
    }
}
