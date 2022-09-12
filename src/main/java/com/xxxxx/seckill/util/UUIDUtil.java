package com.xxxxx.seckill.util;

import java.util.UUID;

/**
 * UUID工具类
 * @author Administrator
 */
public class UUIDUtil {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}