package com.mall.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mall.common.utils.CookieUtils;
import com.mall.common.utils.JsonUtils;
import com.mall.manager.service.TbItemService;
import com.mall.pojo.TbItem;
import com.mall.pojo.TbUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weiwei
 * @create 2019-08-04 9:44
 */
@Controller
public class CartController {

    @Reference
    private TbItemService itemService;
    
    @Value("${SuWei_CART}")
    private String SuWei_CART;
    
    @Value("${CART_EXPIRE}")
    private Integer CART_EXPIRE;
    

    @RequestMapping("/cart/add/{itemId}.html")
    public String addCart(@PathVariable Long itemId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取购物车列表
        List<TbItem> cartList = getCartListFromCookie(request);
        //判断商品是否存在
        boolean flag = false;
        for (TbItem  tbItem : cartList) {
            //存在数量相加
            if(tbItem.getId() == itemId.longValue()){
                flag = true;
                //找到商品，数量相加
                tbItem.setNum(tbItem.getNum() + num);
                //跳出循环
                break;
            }
        }
        //如果不存在，得到一个TbItem对象
        if(!flag){
            //根据商品id查询商品信息。得到一个TbItem对象
            TbItem tbItem = itemService.getItemById(itemId);
            //设置商品数量
            tbItem.setNum(num);
            //取一张图片
            String image = tbItem.getImage();
            if (StringUtils.isNotBlank(image)) {
                tbItem.setImage(image.split(",")[0]);
            }
            //把商品添加到商品列表
            cartList.add(tbItem);
        }
        //写入cookie
        CookieUtils.setCookie(request, response, SuWei_CART, JsonUtils.objectToJson(cartList), CART_EXPIRE, true);
        //返回添加成功页面

        return "cartSuccess";
    }
    
    private List<TbItem> getCartListFromCookie(HttpServletRequest request){
        String json = CookieUtils.getCookieValue(request, SuWei_CART, true);
        //判断json是否为空
        if(StringUtils.isBlank(json)){
            return new ArrayList<>();
        }
        //把json转换成一个商品列表
        List<TbItem> list = JsonUtils.jsonToList(json, TbItem.class);
        return list;
    }
    @RequestMapping("/cart/cart.html")
    public String showCartList(HttpServletRequest request, HttpServletResponse response) {

        //取Cookie购物车商品列表
        List<TbItem> cartList = getCartListFromCookie(request);

        //传递给页面
        request.setAttribute("cartList", cartList);

        return "cart";
    }


}
