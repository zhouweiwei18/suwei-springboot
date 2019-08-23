package com.mall.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mall.common.pojo.ItemCatTreeNode;
import com.mall.common.pojo.SuWeiResult;
import com.mall.content.service.ContentCategoryService;
import com.mall.manager.mapper.TbContentCategoryMapper;
import com.mall.pojo.TbContentCategory;
import com.mall.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public SuWeiResult addContentCategory(long parentId, String name) {
            // 1、接收两个参数：parentId、name
            // 2、向tb_content_category表中插入数据。
            // a)创建一个TbContentCategory对象
            TbContentCategory tbContentCategory = new TbContentCategory();
            // b)补全TbContentCategory对象的属性
            tbContentCategory.setIsParent(false);
            tbContentCategory.setName(name);
            tbContentCategory.setParentId(parentId);
            //排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
            tbContentCategory.setSortOrder(1);
            //状态。可选值:1(正常),2(删除)
            tbContentCategory.setStatus(1);
            tbContentCategory.setCreated(new Date());
            tbContentCategory.setUpdated(new Date());
            // c)向tb_content_category表中插入数据
            tbContentCategoryMapper.insert(tbContentCategory);
            // 3、判断父节点的isparent是否为true，不是true需要改为true。
            TbContentCategory parentNode = tbContentCategoryMapper.selectByPrimaryKey(parentId);
            if (!parentNode.getIsParent()) {
                parentNode.setIsParent(true);
                //更新父节点
                tbContentCategoryMapper.updateByPrimaryKey(parentNode);
            }
            // 4、需要主键返回。
            // 5、返回SuWeiResult，其中包装TbContentCategory对象
            return SuWeiResult.ok(tbContentCategory);
        }

    @Override
    public SuWeiResult updateContentCategory(Long id, String name) {

        //创建一个content_category对象
        TbContentCategory category = new TbContentCategory();
        category.setName(name);
        category.setId(id);

        tbContentCategoryMapper.updateByPrimaryKeySelective(category);

        return SuWeiResult.ok();
    }

    /**
     * 根据id删除节点
     * @param id
     * @return
     */
    @Override
    public SuWeiResult deleteContentCategory(Long id) {
        //判断该节点是否是父节点，是父节点的话就不让删除
        TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        Boolean isParent = contentCategory.getIsParent();
        if (isParent){
            //不让删除
            return SuWeiResult.build(500,"the node is parentNode");
        }else{
            //删除该节点
            tbContentCategoryMapper.deleteByPrimaryKey(id);
            //查询父节点下子节点的个数，如果没有子节点，将父节点改为子节点
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(contentCategory.getParentId());
            //查询
            List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
            if(list.size()==0){//说明父节点下没有子节点了
                //将父节点改为子节点
                TbContentCategory category = new TbContentCategory();
                category.setId(contentCategory.getParentId());
                category.setIsParent(false);
                //更新父亲节点
                tbContentCategoryMapper.updateByPrimaryKeySelective(category);
                return SuWeiResult.ok();
            }

            //父亲节点下还有子节点
            return SuWeiResult.ok();
        }

    }

}
