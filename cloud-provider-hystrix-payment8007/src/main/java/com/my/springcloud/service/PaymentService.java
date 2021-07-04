package com.my.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PaymentService {

    public String paymentInfo_Ok(Integer id){
        return "paymentInfo id: " + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String paymentInfo_TimeOut(Integer id){
        int time = 4;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            log.warn("睡眠被打断");
        }
        return "paymentInfo id:" + id ;
    }

    private String paymentInfo_TimeOutHandler(Integer id){
        return "无法处理 请稍后再试";
    }
}
