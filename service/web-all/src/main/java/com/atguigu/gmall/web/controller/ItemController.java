package com.atguigu.gmall.web.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.vo.SkuDetailVo;
import com.atguigu.gmall.web.feign.SkuDetailFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lfy
 * @Description
 * @create 2022-12-03 11:15
 */
@Controller
public class ItemController {


    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;

    /**
     * 商品详情页
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}.html")
    public String itemPage(@PathVariable("skuId") Long skuId,
                           Model model){

        //远程调用查询详情数据
        Result<SkuDetailVo> skuDetails = skuDetailFeignClient.getSkuDetails(skuId);
        SkuDetailVo detailVo = skuDetails.getData();

        //1、分类视图 {category1Id、category2Id、category3Id、category1Name、category2Name、category3Name}
        model.addAttribute("categoryView",detailVo.getCategoryView());

        //2、sku信息 {基本信息、图片列表}
        model.addAttribute("skuInfo",detailVo.getSkuInfo());

        //3、实时价格
        model.addAttribute("price",detailVo.getPrice());


        //4、所有销售属性集合
        model.addAttribute("spuSaleAttrList",detailVo.getSpuSaleAttrList());

        //5、valuesSkuJson
        model.addAttribute("valuesSkuJson",detailVo.getValuesSkuJson());

        //TODO 6、sku的规格；平台属性

        return "item/index";
    }
}
