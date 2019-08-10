package com.mall.manager.controller;

import com.mall.manager.service.TbItemService;
import com.mall.pojo.TbItem;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author weiwei
 * @create 2019-08-10 13:49
 */
@RestController
@RequestMapping("/item")
public class TbItemController {

    @Resource
    TbItemService tbItemService;


    @GetMapping("/{itemId}")
    public TbItem getItemById(@PathVariable Long itemId) {

        return tbItemService.getItemById(itemId);
    }
}
