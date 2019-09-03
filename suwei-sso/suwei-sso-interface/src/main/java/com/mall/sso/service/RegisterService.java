package com.mall.sso.service;

import com.mall.common.pojo.SuWeiResult;
import com.mall.pojo.TbUser;

/**
 * @author weiwei
 * @create 2019-08-03 9:43
 */
public interface RegisterService {

    SuWeiResult checkData(String param, Integer type);

    SuWeiResult register(TbUser user);
}
