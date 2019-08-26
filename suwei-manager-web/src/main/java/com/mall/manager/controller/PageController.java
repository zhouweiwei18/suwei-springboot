package com.mall.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * 跳转指定页面(通用)
 * @author weiwei
 * @create 2019-08-26 23:21
 */
@Controller
public class PageController {

    @GetMapping("/indexPage/{info}")
    public ModelAndView sendItem(ModelAndView mv, @PathVariable String info){

        mv.setViewName("ftl/admin/"+info);

        return mv;
    }

    @GetMapping("/itemPage/{info}")
    public ModelAndView sendIndex(ModelAndView mv, @PathVariable String info){

        mv.setViewName("ftl/admin/"+info);

        return mv;
    }

    @GetMapping("/contentPage/{info}")
    public ModelAndView sendContent(ModelAndView mv, @PathVariable String info) {

        mv.setViewName("ftl/admin/" + info);

        return mv;
    }
}
