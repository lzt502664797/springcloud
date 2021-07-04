package com.my.springcloud.controller;

import com.my.springcloud.common.CommonResult;
import com.my.springcloud.entity.Payment;
import com.my.springcloud.entity.TestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    /**
     * 服务名（存在多个payment服务）
     * 必须大写
     */
    public static final String URL = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult create(Payment payment){
        return restTemplate.postForObject(URL + "/payment/createPayment",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult getPayment(@PathVariable("id")Long id){
        return restTemplate.getForObject(URL + "/payment/getPayment/" + id,CommonResult.class);
    }

    @GetMapping("/consumer/payment/test")
    public CommonResult test(TestData testData){
         return restTemplate.postForObject(URL + "/payment/test",testData,CommonResult.class);
    }

}
