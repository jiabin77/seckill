package com.xxxxx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxxx.seckill.pojo.Goods;
import com.xxxxx.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiabin
 * @since 2022-03-27
 */
public interface IGoodsService extends IService<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
