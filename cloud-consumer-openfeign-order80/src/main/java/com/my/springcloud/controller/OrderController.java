package com.my.springcloud.controller;

import com.my.springcloud.common.CommonResult;
import com.my.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/getPayment/{id}")
    public CommonResult getPayment(@PathVariable("id")Long id){
        return paymentService.getPayment(id);
    }

    @GetMapping("/consumer/feign/timeout")

    public String testTimeOut(){
        return paymentService.testTimeOut();
    }
}
