package com.mall.sso.service;

import com.mall.common.pojo.SuWeiResult;

public interface TokenService {

	SuWeiResult getUserByToken(String token);
}
