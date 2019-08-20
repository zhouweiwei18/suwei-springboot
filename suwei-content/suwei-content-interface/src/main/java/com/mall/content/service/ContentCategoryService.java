package com.mall.content.service;

import com.mall.common.pojo.ItemCatTreeNode;

import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-20 16:11
 */
public interface ContentCategoryService {

    //根据父id查询内容目录
   List<ItemCatTreeNode> getContentCategoryList(long parentId);
}
