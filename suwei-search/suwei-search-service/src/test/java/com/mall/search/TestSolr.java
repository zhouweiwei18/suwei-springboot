package com.mall.search;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author weiwei
 * @create 2019-08-26 21:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSolr {

    @Autowired
    private SolrClient solrClient;

    @Resource
    private CloudSolrClient cloudSolrClient;

    @Test
    public void addSolr(){
        try {
            //创建文档对象
            SolrInputDocument document = new SolrInputDocument();

            document.addField("id",02333);
            document.addField("item_title","华为手机");
            document.addField("item_price",7800);

            //保存文档对象
            solrClient.add(document);

            //提交
            solrClient.commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void querySolr(){
        try {
            //创建查询对象
            SolrQuery query = new SolrQuery();

            query.setQuery("item_title:手机");
            query.setStart(0);
            query.setRows(10);
            query.setSort("item_price", SolrQuery.ORDER.desc);

            //执行查找
            QueryResponse response =solrClient.query(query);

            //得到文档集合对象
            SolrDocumentList documentList = response.getResults();

            for (SolrDocument doc: documentList) {
                long itemId =  Long.parseLong(doc.getFieldValue("id").toString());
                String item_name = doc.getFieldValue("item_title").toString();
                long item_price = Long.parseLong(doc.getFieldValue("item_price").toString());
                System.out.println("id:"+itemId+",item_name:"+item_name+",item_price:"+item_price);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Solr集群测试
    @Test
    public void addCloud() throws Exception{

        SolrInputDocument document = new SolrInputDocument();

        document.addField("id",02333);
        document.addField("item_title","迪丽热巴");
        document.addField("item_price",7900);

        cloudSolrClient.setDefaultCollection("collection2");
        //保存文档对象
        cloudSolrClient.add(document);

        //提交
        cloudSolrClient.commit();
    }

    //Solr集群测试
    @Test
    public void queryCloud() throws Exception{

        cloudSolrClient.setDefaultCollection("collection2");

        //创建查询对象
        SolrQuery query = new SolrQuery();

        query.setQuery("item_title:迪丽热巴");
        query.setStart(0);
        query.setRows(10);
        query.setSort("item_price", SolrQuery.ORDER.desc);

        //执行查找
        QueryResponse response =cloudSolrClient.query(query);

        //得到文档集合对象
        SolrDocumentList documentList = response.getResults();

        for (SolrDocument doc: documentList) {
            long itemId =  Long.parseLong(doc.getFieldValue("id").toString());
            String item_name = doc.getFieldValue("item_title").toString();
            long item_price = Long.parseLong(doc.getFieldValue("item_price").toString());
            System.out.println("id:"+itemId+",item_name:"+item_name+",item_price:"+item_price);
        }
    }

}
