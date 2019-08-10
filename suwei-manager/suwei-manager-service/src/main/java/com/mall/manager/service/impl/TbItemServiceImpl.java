package com.mall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mall.manager.mapper.TbItemMapper;
import com.mall.manager.service.TbItemService;
import com.mall.pojo.TbItem;
import com.mall.pojo.TbItemExample;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-10 14:39
 */
@Service
public class TbItemServiceImpl implements TbItemService {

    @Resource
    private TbItemMapper itemMapper;

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
}
