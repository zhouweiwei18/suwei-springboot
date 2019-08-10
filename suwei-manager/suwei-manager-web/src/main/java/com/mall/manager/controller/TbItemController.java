package com.mall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.manager.service.TbItemService;
import com.mall.pojo.TbItem;
import org.springframework.web.bind.annotation.*;

/**
 * @author weiwei
 * @create 2019-08-10 13:49
 */
@RestController
@RequestMapping("/item")
public class TbItemController {

    @Reference
    private TbItemService tbItemService;


    @GetMapping("/{itemId}")
    public TbItem getItemById(@PathVariable Long itemId) {

        return tbItemService.getItemById(itemId);
    }
}
