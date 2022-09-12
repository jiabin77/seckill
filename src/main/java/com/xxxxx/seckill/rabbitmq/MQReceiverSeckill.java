package com.xxxxx.seckill.rabbitmq;

import com.xxxxx.seckill.pojo.SeckillRabbitMqMessage;
import com.xxxxx.seckill.pojo.User;
import com.xxxxx.seckill.service.IGoodsService;
import com.xxxxx.seckill.service.IOrderService;
import com.xxxxx.seckill.util.JSONUtils;
import com.xxxxx.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author : jiabin
 * @date :2022/4/15 14:22
 */
@Service
@Slf4j
public class MQReceiverSeckill {

    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IOrderService orderService;

    @RabbitListener(queues = "seckillQueue")
    public void receive(String msg) {
        log.info("接受消息：" + msg);
        //把 转换为对象
        SeckillRabbitMqMessage message = JSONUtils.jsonStr2Object(msg, SeckillRabbitMqMessage.class);
        Long goodsId = message.getGoodsId();
        User user = message.getUser();
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if (goodsVo.getStockCount() < 1) {
            return;
        }
        //判断是否重复抢购
        String seckillOrderJson = (String) redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId);
        if (!StringUtils.isEmpty(seckillOrderJson)) {
            return;
        }
        //下单操作
        orderService.seckill(user, goodsVo);

    }


}