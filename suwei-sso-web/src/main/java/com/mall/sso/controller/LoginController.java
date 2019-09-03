package com.mall.sso.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.common.pojo.SuWeiResult;
import com.mall.common.utils.CookieUtils;
import com.mall.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author weiwei
 * @create 2019-08-03 15:49
 */
@Controller
public class LoginController {

    @Reference
    private LoginService loginService;

    @Value("${TOKEN_KEY}")
    private String TOKEN_KEY;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public SuWeiResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        SuWeiResult result = loginService.userLogin(username, password);
        if (result.getStatus() == 200) {
            String token = result.getData().toString();
            //如果登录成功需要把token写入cookie
            CookieUtils.setCookie(request,response,TOKEN_KEY,token);
        }
        return result;
    }

}
