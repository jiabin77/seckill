package com.xxxxx.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxxx.seckill.exception.GlobalException;
import com.xxxxx.seckill.mapper.UserMapper;
import com.xxxxx.seckill.pojo.User;
import com.xxxxx.seckill.service.IUserService;
import com.xxxxx.seckill.util.CookieUtil;
import com.xxxxx.seckill.util.MD5Util;
import com.xxxxx.seckill.util.UUIDUtil;
import com.xxxxx.seckill.vo.LoginVo;
import com.xxxxx.seckill.vo.RespBean;
import com.xxxxx.seckill.vo.RespBeanEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jiabin
 * @since 2022-03-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //手机号或者密码是否为空
//        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
//            return RespBean.error(RespBeanEnum.LOGINVO_ERROR);
//        }
//        //校验手机号是否通过
//        if (!ValidatorUtil.isMobile(mobile)){
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }
        //根据手机号获取用户名,密码
        User user = userMapper.selectById(mobile);
        if (user == null) {
            return RespBean.error(RespBeanEnum.LOGINVO_ERROR);
        }
        //判断密码是否正确
        if (!MD5Util.formPassToDBPass(password, user.getSalt()).equals(user.getPassword())) {
            return RespBean.error(RespBeanEnum.LOGINVO_ERROR);
        }
        //生成Cookie
        String ticket = UUIDUtil.uuid();
        redisTemplate.opsForValue().set("user" + ticket, user);
        // request.getSession().setAttribute(ticket,user);
        CookieUtil.setCookie(request, response, "userTicket", ticket);
        return RespBean.success(ticket);
    }

    /**
     * 根据cookie 获取用户
     *
     * @param ticket
     * @param request
     * @param response
     * @return
     */
    @Override
    public User getByUserTicket(String ticket, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(ticket)) {
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user" + ticket);
        if (user != null) {
            CookieUtil.setCookie(request, response, "userTicket", ticket);
        }
        return user;
    }

    /*** 更新密码
     *  @param userTicket
     *  @param id
     * @param password
     * @return
     * */
    @Override
    public RespBean updatePassword(String userTicket, Long id, String password, HttpServletRequest request, HttpServletResponse response) {
        User user = getByUserTicket(userTicket, request, response);
        if (user == null) {
            throw new GlobalException(RespBeanEnum.MOBILE_NOT_EXIST);
        }
        user.setPassword(MD5Util.inputPassToDbPass(password, user.getSalt()));
        int result = userMapper.updateById(user);
        if (1 == result) {
            //删除Redis
            redisTemplate.delete("user:" + userTicket);
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.PASSWORD_UPDATE_FAIL);
    }
}
