package com.my.springcloud.provider.mapper;

import com.my.springcloud.entity.Payment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lzt
 * @since 2021-05-25
 */
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {

}
