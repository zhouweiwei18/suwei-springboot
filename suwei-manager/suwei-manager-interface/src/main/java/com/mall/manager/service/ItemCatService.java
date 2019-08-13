package com.mall.manager.service;

import com.mall.common.pojo.ItemCatTreeNode;

import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-13 21:39
 */
public interface ItemCatService {
    List<ItemCatTreeNode> getCatList(Long parentId);
}
