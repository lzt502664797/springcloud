package com.my.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author linzhengtao
 */
@Component
public class PaymentHystrixServiceFallback implements PaymentHystrixService{
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "server is shudown";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "server is shudown";
    }
}
