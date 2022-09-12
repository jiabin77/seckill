package com.xxxxx.seckill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : jiabin
 * @date :2022/7/18 14:17
 */
@Service
@Slf4j
public class MQSenderSeckill {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendsecKillMessage(String message) {
        log.info("发送消息：" + message);
        rabbitTemplate.convertAndSend("seckillExchange", "seckill.msg", message);
    }

}