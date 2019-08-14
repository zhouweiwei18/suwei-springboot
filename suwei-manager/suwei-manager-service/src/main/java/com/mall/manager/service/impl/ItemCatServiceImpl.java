package com.mall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mall.common.pojo.ItemCatTreeNode;
import com.mall.manager.mapper.TbItemCatMapper;
import com.mall.manager.service.ItemCatService;
import com.mall.pojo.TbItemCat;
import com.mall.pojo.TbItemCatExample;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-13 21:50
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<ItemCatTreeNode> getCatList(Long parentId) {
        // 1、根据parentId查询节点列表
        //设置查询条件
        //根据parentId查询子节点列表
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        // 2、转换成ItemCatTreeNode列表。
        List<ItemCatTreeNode> resultList = new ArrayList<>();

        for (TbItemCat tbItemCat : list) {
            ItemCatTreeNode node = new ItemCatTreeNode();
            node.setId(tbItemCat.getId().intValue());
            node.setName(tbItemCat.getName());
            //判断当前的商品类目是否是父节点
            node.setIsParent(tbItemCat.getIsParent());
            //添加到列表
            resultList.add(node);
        }
        // 3、返回。
        return resultList;
    }
}
