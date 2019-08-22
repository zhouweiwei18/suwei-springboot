package com.mall.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author weiwei
 * @create 2019-08-20 15:40
 */
@RestController
public class ContentController {

    @GetMapping("/content/toAdd/{id}")
    public ModelAndView toAdd(ModelAndView mv, @PathVariable long id){

        mv.setViewName("ftl/admin/content-add");
        mv.addObject("id",id);

        System.out.println(id);
        return mv;
    }


    //跳转指定页面(通用)
    @GetMapping("/contentPage/{info}")
    public ModelAndView sendDept(ModelAndView mv, @PathVariable String info){

        mv.setViewName("ftl/admin/"+info);

        return mv;
    }
}
