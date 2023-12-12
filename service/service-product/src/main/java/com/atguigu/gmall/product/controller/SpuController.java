package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.entity.SpuImage;
import com.atguigu.gmall.product.entity.SpuInfo;
import com.atguigu.gmall.product.entity.SpuSaleAttr;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.vo.SpuSaveInfoVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author lfy
 * @Description
 * @create 2022-12-02 9:16
 */
@RequestMapping("/admin/product")
@Api(tags = "spu管理")
@RestController
public class SpuController {


    @Autowired
    SpuInfoService spuInfoService;

    @Autowired
    SpuImageService spuImageService;

    @Autowired
    SpuSaleAttrService spuSaleAttrService;

    @ApiOperation("查询spu定义的所有销售属性名和值")
    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result getSpuSaleAttrList(@PathVariable("spuId") Long spuId){

        List<SpuSaleAttr>  saleAttrs = spuSaleAttrService
                .getSpuSaleAttrList(spuId);
        return Result.ok(saleAttrs);
    }



    @ApiOperation("查询spu的图片列表")
    @GetMapping("/spuImageList/{spuId}")
    public Result getSpuImage(@PathVariable("spuId") Long spuId){

        List<SpuImage> list = spuImageService.lambdaQuery()
                .eq(SpuImage::getSpuId, spuId)
                .list();

        return Result.ok(list);
    }


    /**
     * 400: 请求参数不对。
     *
     * @param category3Id
     * @param pn
     * @param ps
     * @return
     */
    @ApiOperation("分页查询spu")
    @GetMapping("/{pn}/{ps}")
    public Result getSpu(@RequestParam("category3Id") Long category3Id,
                         @PathVariable("pn") Long pn,
                         @PathVariable("ps") Long ps){


        Page<SpuInfo> page = spuInfoService.lambdaQuery()
                .eq(SpuInfo::getCategory3Id, category3Id)
                .page(new Page<SpuInfo>(pn, ps));

        return Result.ok(page);
    }


    /**
     * 1、模型固定：如果前端传递一个复杂的json。一定先编写一个vo来进行数据封装。
     * 2、模型不固定：就直接用Map接受比较方便
     * @param vo
     * @return
     */
    @ApiOperation("保存spu")
    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuSaveInfoVo vo){
        //保存spuinfo
        spuInfoService.saveSpuInfoData(vo);
        return Result.ok();
    }
}
