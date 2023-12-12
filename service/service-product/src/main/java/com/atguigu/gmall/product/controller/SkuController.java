package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.entity.SkuInfo;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.vo.SkuSaveVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lfy
 * @Description
 * @create 2022-12-02 11:45
 */
@Api(tags = "sku管理")
@RequestMapping("/admin/product")
@RestController
public class SkuController {


    @Autowired
    SkuInfoService skuInfoService;

    @ApiOperation("保存sku")
    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuSaveVo vo){

        skuInfoService.saveSkuInfoData(vo);

        //爬虫：拿到商品，录制到数据库？

        return Result.ok();
    }

    @ApiOperation("sku分页列表")
    @GetMapping("/list/{page}/{limit}")
    public Result skuList(@PathVariable("page") Long pn,
                          @PathVariable("limit") Long ps){

        Page<SkuInfo> page = skuInfoService.page(new Page<SkuInfo>(pn, ps));
        return Result.ok(page);
    }
}
