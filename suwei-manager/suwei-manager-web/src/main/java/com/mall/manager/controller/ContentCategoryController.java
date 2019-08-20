package com.mall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.common.pojo.ItemCatTreeNode;
import com.mall.content.service.ContentCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-20 16:06
 */
@RestController
public class ContentCategoryController {

    @Reference
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    public List<ItemCatTreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0") Long parentId){
        return contentCategoryService.getContentCategoryList(parentId);
    }

}
