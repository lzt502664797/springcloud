package com.my.springcloud.provider.service.impl;

import com.my.springcloud.entity.Payment;
import com.my.springcloud.provider.mapper.PaymentMapper;
import com.my.springcloud.provider.service.IPaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lzt
 * @since 2021-05-25
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements IPaymentService {

    @Autowired
    private PaymentMapper paymentMapper;


    public Payment getPayment(Long id){
        Payment payment = paymentMapper.selectById(id);
        return payment;
    }

    public int createPayment(Payment payment){
        return paymentMapper.insert(payment);
    }



}
