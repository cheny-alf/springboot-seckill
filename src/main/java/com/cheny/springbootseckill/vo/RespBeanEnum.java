package com.cheny.springbootseckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString@Getter
@AllArgsConstructor
public enum RespBeanEnum {
    //服务端异常
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"服务端异常"),
    //登录异常
    LOGIN_ERROR(500210,"登录用户名或者密码有误"),
    MOBILE_ERROR(500211,"手机号码格式出错"),

    //全局异常
    BIND_ERROR(500212,"参数校验异常"),

    //秒杀模块
    EMPTY_STOCK(500500,"空库存"),
    REPEAT_ERROR(500501,"重复购买")
    ;
    private final  Integer code;
    private final String message;

}
