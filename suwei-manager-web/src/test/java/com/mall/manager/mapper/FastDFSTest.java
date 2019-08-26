package com.mall.manager.mapper;

import com.mall.common.utils.FastDFSClient;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author weiwei
 * @create 2019-08-13 10:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FastDFSTest {

    @Test
    public void testUpload() throws Exception {
        //创建一个配置文件。文件名任意。内容就是tracker服务器的地址。
        //使用全局对象加载配置文件。
        ClientGlobal.init("E:/IDEA_Project/suwei-manager/suwei-manager-web/src/main/resources/conf/client.conf");
        //创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过TrackClient获得一个TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //创建一个StrorageServer的引用，可以是null
        StorageServer storageServer = null;
        //创建一个StorageClient，参数需要TrackerServer和StrorageServer
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        //使用StorageClient上传文件。 D:\Picture\dog.jpg
        String[] strings = storageClient.upload_file("D:/Picture/monkey.jpg", "jpg", null);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    //工具类上传文件测试
    @Test
    public void testFastDfsClient() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("E:/IDEA_Project/suwei-manager/suwei-manager-web/src/main/resources/conf/client.conf");
        String file = fastDFSClient.uploadFile("D:/Picture/5ae686cfc8cfb72e8f0322fc.jpg");
        System.out.println(file);
    }
}
