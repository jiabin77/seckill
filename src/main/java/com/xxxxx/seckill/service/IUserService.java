package com.xxxxx.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxxx.seckill.pojo.User;
import com.xxxxx.seckill.vo.LoginVo;
import com.xxxxx.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jiabin
 * @since 2022-03-24
 */
public interface IUserService extends IService<User> {

    RespBean login(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    User getByUserTicket(String ticket, HttpServletRequest request, HttpServletResponse response);

    RespBean updatePassword(String userTicket, Long id, String password,HttpServletRequest request, HttpServletResponse response);
}
