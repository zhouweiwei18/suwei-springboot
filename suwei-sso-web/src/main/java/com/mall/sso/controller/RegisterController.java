package com.mall.sso.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.common.pojo.SuWeiResult;
import com.mall.pojo.TbUser;
import com.mall.sso.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author weiwei
 * @create 2019-08-02 16:12
 */
@Controller
public class RegisterController {

    @Reference
    private RegisterService registerService;

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public SuWeiResult checkData(@PathVariable String param, @PathVariable Integer type) {
        SuWeiResult result = registerService.checkData(param, type);
        return result;
    }

    @RequestMapping(value="/user/register", method= RequestMethod.POST)
    @ResponseBody
    public SuWeiResult register(TbUser user) {
        SuWeiResult SuWeiResult = registerService.register(user);
        return SuWeiResult;
    }

}
