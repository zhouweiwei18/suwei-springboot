package com.mall.sso.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mall.common.pojo.SuWeiResult;
import com.mall.common.utils.JsonUtils;
import com.mall.manager.mapper.TbUserMapper;
import com.mall.pojo.TbUser;
import com.mall.pojo.TbUserExample;
import com.mall.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author weiwei
 * @create 2019-08-03 15:40
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;

    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;


    @Override
    public SuWeiResult userLogin(String username, String password) {
        // 1、判断用户和密码是否正确
        //根据用户名查询用户信息
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        //执行查询
        List<TbUser> list = userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            //返回登录失败
            return SuWeiResult.build(400, "用户名或密码错误");
        }
        //取用户信息
        TbUser user = list.get(0);
        //判断密码是否正确
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            // 2、如果不正确，返回登录失败
            return SuWeiResult.build(400, "用户名或密码错误");
        }
        // 3、如果正确生成token。
        String token = UUID.randomUUID().toString();
        // 4、把用户信息写入redis，key：token value：用户信息
        user.setPassword(null);
        redisTemplate.opsForValue().set(REDIS_SESSION_KEY + ":" + token, user);
        //设置Session过期时间
        redisTemplate.expire(REDIS_SESSION_KEY + ":" + token, SESSION_EXPIRE, TimeUnit.MINUTES);
        // 6、把token返回
        return SuWeiResult.ok(token);
    }
}
