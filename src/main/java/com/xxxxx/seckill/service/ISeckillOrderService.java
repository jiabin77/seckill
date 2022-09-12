package com.xxxxx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxxx.seckill.pojo.SeckillOrder;
import com.xxxxx.seckill.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiabin
 * @since 2022-03-27
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    /**
     * 获取秒杀结果：成功，-1：秒杀失败，0：排队中
     * @param user
     * @param goodsId
     * @return
     */
    Long getResult(User user, Long goodsId);
}
