package com.mall.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author weiwei
 * @create 2019-08-20 10:27
 */
@Controller
public class IndexController {

    @RequestMapping({"/index", "/", "index.html"})
    public String shoeIndex(Model model){

        return "index";
    }
}
