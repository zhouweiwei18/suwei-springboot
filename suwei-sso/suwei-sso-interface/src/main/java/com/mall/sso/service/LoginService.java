package com.mall.sso.service;

import com.mall.common.pojo.SuWeiResult;

/**
 * @author weiwei
 * @create 2019-08-03 15:39
 */
public interface LoginService {

    SuWeiResult userLogin(String username, String password);

}
