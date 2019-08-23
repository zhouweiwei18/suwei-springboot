package com.mall.content.service;

import com.mall.common.pojo.SuWeiResult;
import com.mall.pojo.TbContent;

import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-23 9:44
 */
public interface ContentService {

    List<TbContent> getContentListByCid(long cid);

    //添加内容
    SuWeiResult addContent(TbContent content);

    //查询Content表
    List<TbContent> queryContent();
}
