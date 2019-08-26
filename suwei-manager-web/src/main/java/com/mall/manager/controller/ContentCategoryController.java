package com.mall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.common.pojo.ItemCatTreeNode;
import com.mall.common.pojo.SuWeiResult;
import com.mall.content.service.ContentCategoryService;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/content/category/create")
    public SuWeiResult createCategory(Long parentId, String name) {
        return contentCategoryService.addContentCategory(parentId, name);
    }

    @RequestMapping(value = "/content/category/update",method= RequestMethod.POST)
    public SuWeiResult updateCategory(Long id, String name) {
        return contentCategoryService.updateContentCategory(id, name);
    }

    @RequestMapping(value = "/content/category/delete",method= RequestMethod.POST)
    public SuWeiResult deleteCategory(Long id) {
        return contentCategoryService.deleteContentCategory(id);
    }

}
