package com.mall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.common.pojo.SuWeiResult;
import com.mall.content.service.ContentService;
import com.mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-20 15:40
 */
@RestController
public class ContentController {

    @Reference
    private ContentService contentService;

    @RequestMapping(value = "/content/list", method = RequestMethod.POST)
    @ResponseBody
    public List<TbContent> queryContent() {

        List<TbContent> list = contentService.queryContent();

        return list;
    }

    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public SuWeiResult addContent(TbContent content) {
        //调用服务把内容数据保存到数据库
        SuWeiResult result = contentService.addContent(content);
        return result;
    }


    @GetMapping("/content/toAdd/{id}")
    public ModelAndView toAdd(ModelAndView mv, @PathVariable long id) {

        mv.setViewName("ftl/admin/content-add");
        mv.addObject("id", id);

        System.out.println(id);
        return mv;
    }


    //跳转指定页面(通用)
    /*@GetMapping("/contentPage/{info}")
    public ModelAndView sendDept(ModelAndView mv, @PathVariable String info) {

        mv.setViewName("ftl/admin/" + info);

        return mv;
    }*/
}
