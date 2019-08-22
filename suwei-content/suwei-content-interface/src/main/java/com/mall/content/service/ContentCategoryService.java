package com.mall.content.service;

import com.mall.common.pojo.ItemCatTreeNode;
import com.mall.common.pojo.SuWeiResult;

import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-20 16:11
 */
public interface ContentCategoryService {

    //根据父id查询内容目录
    List<ItemCatTreeNode> getContentCategoryList(long parentId);

    SuWeiResult addContentCategory(long parentId, String name);

    //节点重命名
    SuWeiResult updateContentCategory(Long id, String name);
}
