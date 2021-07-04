package com.my.springcloud.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 
 * </p>
 *
 * @author lzt
 * @since 2021-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Payment {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String serial;


}
