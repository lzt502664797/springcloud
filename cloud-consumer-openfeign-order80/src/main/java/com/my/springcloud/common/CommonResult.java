package com.my.springcloud.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResult<T> {
    private int status;
    private String msg;
    private T data;


    public CommonResult(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public CommonResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
