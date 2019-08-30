package com.mall.manager;


import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author weiwei
 * @create 2019-08-30 9:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActiveMQTest {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    public void testQueueProducer() throws Exception {
        // 发送消息队列
        ActiveMQTopic itemAddTopic = new ActiveMQTopic("itemAddTopic");
        jmsMessagingTemplate.convertAndSend(itemAddTopic, 111);
    }

}
