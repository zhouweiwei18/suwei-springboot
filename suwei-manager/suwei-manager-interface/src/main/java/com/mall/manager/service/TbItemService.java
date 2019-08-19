package com.mall.manager.service;

import com.mall.common.pojo.SuWeiResult;
import com.mall.pojo.TbItem;

import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-10 14:38
 */
public interface TbItemService {

    //根据id查询商品
    TbItem getItemById(Long itemId);

    //查询商品所有信息
    List<TbItem> getItemList();

    //添加商品
    SuWeiResult addItem(TbItem item, String desc);
}
