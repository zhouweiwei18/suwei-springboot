package com.mall.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.mall.common.pojo.SearchItem;
import com.mall.common.pojo.SuWeiResult;
import com.mall.search.mapper.SearchItemMapper;
import com.mall.search.service.SearchItemService;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-26 22:38
 */
@Service
public class SearchItemServiceImpl implements SearchItemService {

    @Autowired
    private CloudSolrClient solrClient;
    //private SolrClient solrClient;

    @Value("${DEFAULT_COLLECTION}")
    private String DEFAULT_COLLECTION;//默认collection

    @Autowired
    private SearchItemMapper itemMapper;

    @Override
    public SuWeiResult importItems() {
        try {
            //集群版设置默认collection
            solrClient.setDefaultCollection(DEFAULT_COLLECTION);
            //查询商品列表
            List<SearchItem> itemList = itemMapper.getItemList();
            //导入索引库
            for (SearchItem searchItem : itemList) {
                //创建文档对象
                SolrInputDocument document = new SolrInputDocument();
                //向文档中添加域
                document.addField("id", searchItem.getId());
                document.addField("item_title", searchItem.getTitle());
                document.addField("item_sell_point", searchItem.getSell_point());
                document.addField("item_price", searchItem.getPrice());
                document.addField("item_image", searchItem.getImage());
                document.addField("item_category_name", searchItem.getCategory_name());
                //写入索引库
                solrClient.add(document);
            }
            //提交
            solrClient.commit();
            //返回成功
            return SuWeiResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return SuWeiResult.build(500, "商品导入失败");
        }
    }
}
