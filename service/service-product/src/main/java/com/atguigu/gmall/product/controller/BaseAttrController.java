package com.atguigu.gmall.product.controller;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.entity.BaseAttrInfo;
import com.atguigu.gmall.product.entity.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 平台属性
 */
@Api(tags = "平台属性")
@RequestMapping("/admin/product")
@RestController
public class BaseAttrController {


    @Autowired
    BaseAttrInfoService baseAttrInfoService;

    @Autowired
    BaseAttrValueService baseAttrValueService;

    @ApiOperation("根据分类id获取平台属性名和值")
    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result getBaseAttr(@PathVariable("category1Id") Long category1Id,
                              @PathVariable("category2Id") Long category2Id,
                              @PathVariable("category3Id") Long category3Id){

        List<BaseAttrInfo> attrInfos = baseAttrInfoService
                .getBaseAttrAndValue(category1Id,
                category2Id,
                category3Id);

        return Result.ok(attrInfos);
    }

    @ApiOperation("保存/修改 平台属性（名和值）")
    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){

        if(baseAttrInfo.getId()!=null){
            //修改
            baseAttrInfoService.updateAttrInfo(baseAttrInfo);
        }else {
            //保存
            baseAttrInfoService.saveAttrInfo(baseAttrInfo);
        }


        return Result.ok();
    }


    @ApiOperation("查询某个平台属性的所有值")
    @GetMapping("/getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable("attrId") Long attrId){

        List<BaseAttrValue> baseAttrValues =  baseAttrValueService
                .getAttrValueList(attrId);
        return Result.ok(baseAttrValues);
    }
}
