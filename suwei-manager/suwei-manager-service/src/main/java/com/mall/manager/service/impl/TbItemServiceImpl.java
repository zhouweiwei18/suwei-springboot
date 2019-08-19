package com.mall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mall.common.pojo.SuWeiResult;
import com.mall.common.utils.IDUtils;
import com.mall.manager.mapper.TbItemDescMapper;
import com.mall.manager.mapper.TbItemMapper;
import com.mall.manager.service.TbItemService;
import com.mall.pojo.TbItem;
import com.mall.pojo.TbItemDesc;
import com.mall.pojo.TbItemExample;
import org.apache.activemq.command.ActiveMQTopic;

import javax.annotation.Resource;


import java.util.Date;
import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-10 14:39
 */
@Service
public class TbItemServiceImpl implements TbItemService {

    @Resource
    private TbItemMapper itemMapper;

    @Resource
    private TbItemDescMapper tbItemDescMapper;


    @Override
    public TbItem getItemById(Long itemId) {

        //根据主键查询
        //TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andIdEqualTo(itemId);
        //执行查询
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<TbItem> getItemList() {

        TbItemExample example = new TbItemExample();

        List<TbItem> list = itemMapper.selectByExample(example);

        return list;
    }

    @Override
    public SuWeiResult addItem(TbItem item, String desc) {
        // 1、生成商品id
        long itemId = IDUtils.genItemId();
        // 2、补全TbItem对象的属性
        item.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        // 3、向商品表插入数据
        itemMapper.insert(item);
        // 4、创建一个TbItemDesc对象
        TbItemDesc itemDesc = new TbItemDesc();
        // 5、补全TbItemDesc的属性
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);
        // 6、向商品描述表插入数据
        tbItemDescMapper.insert(itemDesc);
        // 7、E3Result.ok()
        return SuWeiResult.ok();
    }
}
