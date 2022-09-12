package com.xxxxx.seckill;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SeckillApplicationTests {


    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisScript<Boolean> redisScript;

    @Test
    void contextLoads() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Boolean isLock = valueOperations.setIfAbsent("k1", "v1"); //占位 如果设置的值，已经存在无法设置成功
        if (isLock) {
            valueOperations.set("name", "jiabin");
            String name = (String) valueOperations.get("name");
            System.out.println("name=" + name);
            redisTemplate.delete("k1"); //删除锁
        } else {
            System.out.println("没有锁住");
        }
    }

    @Test
    void contextLoads2() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Boolean isLock = valueOperations.setIfAbsent("k1", "v1", 5, TimeUnit.SECONDS); //添加时间,5秒后删除锁，但是这样会混乱，第二个线程的锁会删掉第三个线程的锁
        if (isLock) {
            valueOperations.set("name", "jiabin");
            String name = (String) valueOperations.get("name");
            System.out.println("name=" + name);
            Integer.parseInt("xxxxx");
            redisTemplate.delete("k1"); //删除锁
        } else {
            System.out.println("没有锁住");
        }
    }


    //1.获取锁  2.比较锁  3.删除锁  lua脚本
    @Test
    void contextLoads3() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String value = UUID.randomUUID().toString();
        //给锁添加一个过期时间，防止应用在运行过程中抛出异常导致锁无法及时得到释放
        Boolean isLock = valueOperations.setIfAbsent("k1", value, 5, TimeUnit.SECONDS);
        //没人占位
        if (isLock) {
            valueOperations.set("name", "jiajia");
            String name = (String) valueOperations.get("name");
            System.out.println(name);
            System.out.println(valueOperations.get("k1"));
            //释放锁
            Boolean result = (Boolean) redisTemplate.execute(redisScript, Collections.singletonList("k1"), value);
            System.out.println(result);
        } else {
            //有人占位，停止/暂缓 操作
            System.out.println("有线程在使用，请稍后"); }
        }


    }
