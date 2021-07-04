package com.my.springcloud.service;

import com.my.springcloud.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentService {
    @GetMapping("/payment/getPayment/{id}")
    CommonResult getPayment(@PathVariable("id")Long id);

    @GetMapping("/payment/feign/timeout")
    String testTimeOut();
}
