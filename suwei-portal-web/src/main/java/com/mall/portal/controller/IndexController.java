package com.mall.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.content.service.ContentService;
import com.mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-20 10:27
 */
@Controller
public class IndexController {

    @Reference
    private ContentService contentService;

    @Value("${CONTENT_BANNER_ID}")
    private long CONTENT_BANNER_ID;

    @RequestMapping({"/index", "/", "index.html"})
    public String showIndex(Model model){

        //准备首页需要的数据
        List<TbContent> contentList = contentService.getContentListByCid(CONTENT_BANNER_ID);

        model.addAttribute("ad1List",contentList);

        return "index";
    }
}
