package com.mall.content.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.mall.common.pojo.SuWeiResult;
import com.mall.content.service.ContentService;
import com.mall.manager.mapper.TbContentMapper;
import com.mall.pojo.TbContent;
import com.mall.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-23 9:47
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

    @Override
    public List<TbContent> getContentListByCid(Long cid) {
        //加入缓存
        try {
            //查询
            List<TbContent> list = (List<TbContent>)redisTemplate.opsForHash().get(CONTENT_KEY, cid.toString());
            System.out.println("read redis catch data...");
            if(list!=null&&!list.isEmpty()){
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        // 向缓存中添加数据
        try {
            redisTemplate.opsForHash().put(CONTENT_KEY,cid.toString(),list);
            System.out.println("write redis catch data...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public SuWeiResult addContent(TbContent content) {
        //将内容数据插入到内容表
        content.setCreated(new Date());
        content.setUpdated(new Date());
        //插入到数据库
        contentMapper.insert(content);
        //缓存同步
        redisTemplate.opsForHash().delete(CONTENT_KEY,content.getCategoryId().toString());
        return SuWeiResult.ok();
    }

    @Override
    public List<TbContent> queryContent() {

        TbContentExample example = new TbContentExample();

        List<TbContent> list = contentMapper.selectByExample(example);

        return list;
    }
}
