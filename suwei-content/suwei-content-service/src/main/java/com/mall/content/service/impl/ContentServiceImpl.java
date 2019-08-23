package com.mall.content.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.mall.common.pojo.SuWeiResult;
import com.mall.content.service.ContentService;
import com.mall.manager.mapper.TbContentMapper;
import com.mall.pojo.TbContent;
import com.mall.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-23 9:47
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    TbContentMapper contentMapper;

    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        return list;
    }

    @Override
    public SuWeiResult addContent(TbContent content) {
            //将内容数据插入到内容表
            content.setCreated(new Date());
            content.setUpdated(new Date());
            //插入到数据库
            contentMapper.insert(content);
            return SuWeiResult.ok();
    }
}
