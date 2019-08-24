package com.mall.content;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author weiwei
 * @create 2019-08-24 9:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 设置缓存数据
     */
    @Test
    public void test(){
        stringRedisTemplate.opsForValue().set("abc","123");
        Assert.assertEquals("123",stringRedisTemplate.opsForValue().get("abc"));
    }
    @Test
    public void test02(){
        String s = stringRedisTemplate.opsForValue().get("abc");
        System.out.println(s);
    }

}
