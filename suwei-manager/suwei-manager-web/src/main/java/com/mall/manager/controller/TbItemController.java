package com.mall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.common.pojo.SuWeiResult;
import com.mall.manager.service.TbItemService;
import com.mall.pojo.TbItem;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-10 13:49
 */
@RestController
public class TbItemController {

    @Reference
    private TbItemService tbItemService;


    //根据商品id查询
    @GetMapping("/item/{itemId}")
    public TbItem getItemById(@PathVariable Long itemId) {

        return tbItemService.getItemById(itemId);
    }

    @RequestMapping("/item/list")
    public List<TbItem> getItemList(ModelAndView mv) {

        //查询所有信息
        List<TbItem> list = tbItemService.getItemList();

        return list;
    }

    //添加商品
    @RequestMapping("/item/save")
    public SuWeiResult getItemList(TbItem item, String desc) {

        SuWeiResult result = tbItemService.addItem(item, desc);

        return result;
    }

    //跳转指定页面(通用)
    @GetMapping("/itemPage/{info}")
    public ModelAndView sendDept(ModelAndView mv,@PathVariable String info){

        mv.setViewName("ftl/admin/"+info);

        return mv;
    }

    //到首页面
    @RequestMapping("/")
    public ModelAndView toIndex(ModelAndView mv){

        mv.setViewName("ftl/admin/index");

        return mv;
    }

}
