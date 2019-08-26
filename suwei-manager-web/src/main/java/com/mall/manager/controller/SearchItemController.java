package com.mall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.common.pojo.SuWeiResult;
import com.mall.search.service.SearchItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author weiwei
 * @create 2019-08-26 23:17
 */
@Controller
public class SearchItemController {

    @Reference(timeout = 300000)
    private SearchItemService searchItemService;

    @RequestMapping("/index/item/import")
    @ResponseBody
    public SuWeiResult importItemIndex() {
        SuWeiResult result = searchItemService.importItems();
        return result;
    }
}
