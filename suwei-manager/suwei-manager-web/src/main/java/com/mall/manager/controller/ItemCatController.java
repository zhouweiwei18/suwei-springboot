package com.mall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.common.pojo.ItemCatTreeNode;
import com.mall.manager.service.ItemCatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-13 21:38
 */
@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

    @Reference
    private ItemCatService itemCatService;

    @RequestMapping("/list")
    public List<ItemCatTreeNode> getItemCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<ItemCatTreeNode> list = itemCatService.getCatList(parentId);
        return list;
    }
}
