package com.xxxxx.seckill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiabin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    private long code;
    private String message;
    private Object object;

    /**
     * 成功返回结果
     * @return
     */
    public static RespBean success(){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(),RespBeanEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功返回结果
     * @return
     */
    public static RespBean success(Object object){
        return new RespBean(RespBeanEnum.SUCCESS.getCode(),RespBeanEnum.SUCCESS.getMessage(), object);
    }

    /**
     * 失败返回结果
     * @return
     */
    public static RespBean error(RespBeanEnum respBeanEnum){
        return new RespBean(RespBeanEnum.ERROR.getCode(), respBeanEnum.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @return
     */
    public static RespBean error(RespBeanEnum respBeanEnum,Object object){
        return new RespBean(RespBeanEnum.ERROR.getCode(), respBeanEnum.getMessage(), object);
    }
}