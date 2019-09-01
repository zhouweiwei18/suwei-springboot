package com.mall.search.message;

import com.mall.common.pojo.SearchItem;
import com.mall.search.mapper.SearchItemMapper;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component
public class ItemAddMessageReceiver {

    @Resource
    private SearchItemMapper searchItemMapper;

    @Resource
    private CloudSolrClient solrClient;
    // private SolrClient solrClient;

    @Value("${DEFAULT_COLLECTION}")
    private String DEFAULT_COLLECTION;//默认collection2

    @JmsListener(destination = "itemAddTopic", containerFactory = "jmsTopicListenerContainerFactory")
    public void itemAddReceiver(Long msg) {
        try {
            // 0、等待1s让e3-manager-service提交完事务，商品添加成功
            Thread.sleep(1000);
            // 1、根据商品id查询商品信息
            SearchItem searchItem = searchItemMapper.getItemById(msg);
            // 2、创建一SolrInputDocument对象。
            SolrInputDocument document = new SolrInputDocument();
            // 3、使用SolrServer对象写入索引库。
            document.addField("id", searchItem.getId());
            document.addField("item_title", searchItem.getTitle());
            document.addField("item_sell_point", searchItem.getSell_point());
            document.addField("item_price", searchItem.getPrice());
            document.addField("item_image", searchItem.getImage());
            document.addField("item_category_name", searchItem.getCategory_name());
            // 5、向索引库中添加文档。
            solrClient.setDefaultCollection(DEFAULT_COLLECTION);
            solrClient.add(document);
            solrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
