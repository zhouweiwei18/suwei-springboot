package com.mall.search.mapper;

import com.mall.common.pojo.SearchItem;

import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-26 21:45
 */
public interface SearchItemMapper {
    List<SearchItem> getItemList();

    //根据id查询商品(用于消息队列同步消息)
    SearchItem getItemById(Long itemId);
}
