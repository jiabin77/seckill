package com.xxxxx.seckill.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 秒杀信息
 * @author : jiabin
 * @date :2022/7/18 14:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillRabbitMqMessage {
    private User user;
    private Long goodsId;
}