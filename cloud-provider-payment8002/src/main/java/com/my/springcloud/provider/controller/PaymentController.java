package com.my.springcloud.provider.controller;


import com.my.springcloud.provider.common.CommonResult;
import com.my.springcloud.entity.Payment;
import com.my.springcloud.entity.TestData;
import com.my.springcloud.provider.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lzt
 * @since 2021-05-25
 */
@RestController
@RequestMapping("//payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @Value("${server.port}")
    private String servePort;

    @GetMapping("/getPayment/{id}")
    public CommonResult getPayment(@PathVariable("id")Long id){
        Payment payment = paymentService.getPayment(id);
        return new CommonResult(200,"success,serverPort: " + servePort,payment);
    }

    @PostMapping("/post")
    public String testPost(Payment payment){
        String serial = payment.getSerial();

        return serial;
    }

    @GetMapping("/get/{id}")
    public String testGet(@PathVariable("id")Long id){
        return id.toString();
    }

    @PostMapping("/createPayment")
    public CommonResult createPayment(@RequestBody Payment payment){
        int payment1 = paymentService.createPayment(payment);
        if (payment1 == 1){
            return new CommonResult(200,"成功创建");
        } else {
            return new CommonResult(500,"创建失败");
        }
    }

    @PostMapping("/test")
    public CommonResult test(@RequestBody TestData testData){
        return new CommonResult(200 ,"success",testData);
    }

    @GetMapping("/feign/timeout")
    public String testTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return servePort;
    }
}
