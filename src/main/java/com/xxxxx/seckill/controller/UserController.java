package com.xxxxx.seckill.controller;


import com.xxxxx.seckill.pojo.User;
import com.xxxxx.seckill.rabbitmq.MQSender;
import com.xxxxx.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jiabin
 * @since 2022-03-24
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MQSender mqSender;

    /**
     * 用户测试
     *
     * @param user
     * @return
     */
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user) {
        return RespBean.success(user);
    }


    /**
     * 测试发送RabbitMQ消息
     */
    @RequestMapping("/mq")
    @ResponseBody
    public void mq() {
        mqSender.send("Hello");
    }

    /**
     * fanout 模式   （广播模式）
     * 测试发送RabbitMQ消息
     */
    @RequestMapping("/mq/fanout")
    @ResponseBody
    public void mq01() {
        mqSender.send("Hello");
    }

    /**
     * direct 模式   （路由key）
     * 测试发送RabbitMQ消息
     */
    @RequestMapping("/mq/direct01")
    @ResponseBody
    public void mq02() {
        mqSender.send01("Hello,Red");
    }

    /**
     * direct 模式   （路由key）  绑定路由key 指定接收者
     * 测试发送RabbitMQ消息
     */
    @RequestMapping("/mq/direct02")
    @ResponseBody
    public void mq03() {
        mqSender.send02("Hello,Green");
    }

    /*** 测试发送RabbitMQ消息 */
    @RequestMapping("/mq/topic01")
    @ResponseBody
    public void mq04() {
        mqSender.send03("Hello,Red");
    }

    /*** 测试发送RabbitMQ消息 */
    @RequestMapping("/mq/topic02")
    @ResponseBody
    public void mq05() {
        mqSender.send04("Hello,Green");
    }

    /*** 测试发送RabbitMQ消息 */
    @RequestMapping("/mq/header01")
    @ResponseBody
    public void mq06() {
        mqSender.send05("Hello,Header01");
    }

    /*** 测试发送RabbitMQ消息 */
    @RequestMapping("/mq/header02")
    @ResponseBody
    public void mq07() {
        mqSender.send06("Hello,Header02");
    }

}
