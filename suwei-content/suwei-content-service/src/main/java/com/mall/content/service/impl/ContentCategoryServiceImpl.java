package com.mall.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mall.common.pojo.ItemCatTreeNode;
import com.mall.content.service.ContentCategoryService;
import com.mall.manager.mapper.TbContentCategoryMapper;
import com.mall.pojo.TbContentCategory;
import com.mall.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-20 16:16
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    //根据父id查询内容目录集合
    @Override
    public List<ItemCatTreeNode> getContentCategoryList(long parentId) {

        //创建查询条件
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);

        //创建一个用于返回的List<ItemCatTreeNode>
        List<ItemCatTreeNode> result = new ArrayList<ItemCatTreeNode>();

        //遍历当前内容目录集合，封装ItemCatTreeNode(通用)
        for (TbContentCategory contentCategory:list) {
            //创建一个新的ItemCatTreeNode节点
            ItemCatTreeNode node = new ItemCatTreeNode();
            node.setId(contentCategory.getId().intValue());
            node.setName(contentCategory.getName());
            //判断当前的商品类目是否是父节点
            node.setIsParent(contentCategory.getIsParent());
            //将节点添加到集合
            result.add(node);
        }

        return result;
    }
}
