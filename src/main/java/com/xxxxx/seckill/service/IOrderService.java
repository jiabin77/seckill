package com.xxxxx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxxx.seckill.pojo.Order;
import com.xxxxx.seckill.pojo.User;
import com.xxxxx.seckill.vo.GoodsVo;
import com.xxxxx.seckill.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiabin
 * @since 2022-03-27
 */
public interface IOrderService extends IService<Order> {

    Order seckill(User user, GoodsVo goods);

    /*** 订单详情 * @param orderId * @return */
    OrderDetailVo detail(Long orderId);
}
