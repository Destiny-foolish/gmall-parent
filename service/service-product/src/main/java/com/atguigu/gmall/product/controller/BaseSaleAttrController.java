package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.entity.BaseSaleAttr;
import com.atguigu.gmall.product.service.BaseSaleAttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lfy
 * @Description
 * @create 2022-12-02 9:42
 */
@Api(tags = "销售属性管理")
@RequestMapping("/admin/product")
@RestController
public class BaseSaleAttrController {


    @Autowired
    BaseSaleAttrService baseSaleAttrService;

    @ApiOperation("品牌列表")
    @GetMapping("/baseSaleAttrList")
    public Result getSaleAttrList(){
        List<BaseSaleAttr> list = baseSaleAttrService.list();
        return Result.ok(list);
    }
}
