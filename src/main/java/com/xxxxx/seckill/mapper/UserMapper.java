package com.xxxxx.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxxx.seckill.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiabin
 * @since 2022-03-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
