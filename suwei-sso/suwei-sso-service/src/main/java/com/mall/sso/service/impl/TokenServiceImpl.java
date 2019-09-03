package com.mall.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mall.common.pojo.SuWeiResult;
import com.mall.pojo.TbUser;
import com.mall.sso.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author weiwei
 * @create 2019-08-03 16:51
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;

    @Override
    public SuWeiResult getUserByToken(String token) {
        TbUser tbUser = (TbUser) redisTemplate.opsForValue().get(REDIS_SESSION_KEY + ":" + token);
        if (tbUser == null) {
            return SuWeiResult.build(201, "用户登录信息已经过期！");
        }
        redisTemplate.expire(REDIS_SESSION_KEY + ":" + token, SESSION_EXPIRE, TimeUnit.MINUTES);
        return SuWeiResult.ok(tbUser);
    }
}
