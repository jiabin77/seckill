package com.xxxxx.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 返回状态枚举
 */
@ToString
@Getter
@AllArgsConstructor
public enum RespBeanEnum {

    //通用状态码
    SUCCESS(200, "success"),
    ERROR(500, "服务端异常"),
    //登录模块5002xx
    SESSION_ERROR(500210, "session不存在或者已经失效"),
    LOGINVO_ERROR(500211, "用户名或者密码错误"),
    MOBILE_ERROR(500212, "手机号码格式错误"),
    BIND_ERROR(500213,"手机号码格式错误"),
    EMPTY_STOCK(500214,"库存商品不足"),
    REPEATE_ERROR(500215,"商品不能重复抢购"),
    MOBILE_NOT_EXIST(500216, "手机号码不存在"),
    PASSWORD_UPDATE_FAIL(500217, "密码更新失败"),
    ORDER_NOT_EXIST(500218,"订单不存在");


    private final Integer code;
    private final String message;
}
