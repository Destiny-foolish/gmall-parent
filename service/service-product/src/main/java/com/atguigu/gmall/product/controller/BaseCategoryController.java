package com.atguigu.gmall.product.controller;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.entity.BaseCategory1;
import com.atguigu.gmall.product.entity.BaseCategory2;
import com.atguigu.gmall.product.entity.BaseCategory3;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.service.BaseCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequestMapping("/admin/product")
@Api(tags = "三级分类管理")
@RestController
public class BaseCategoryController {


    @Autowired
    BaseCategoryService baseCategoryService;

    @Autowired
    BaseCategory2Service baseCategory2Service;

    @Autowired
    BaseCategory3Service baseCategory3Service;

    /**
     * 查询所有一级分类
     * @return
     */
    @ApiOperation("查询所有一级分类")
    @GetMapping("/getCategory1")
    public Result getCategory1(){
        List<BaseCategory1> category1s = baseCategoryService.list();
        return Result.ok(category1s);
    }


    /**
     * 查询某个一级分类下的所有二级分类
     * @return
     */
    @ApiOperation(" 查询某个一级分类下的所有二级分类")
    @GetMapping("/getCategory2/{category1Id}")
    public Result getCategory2(@PathVariable("category1Id") Long category1Id){
        //select * from base_category2 where category1_id=?
        List<BaseCategory2> category2s = baseCategory2Service
                .getCategory2sByC1Id(category1Id);

        return Result.ok(category2s);
    }

    /**
     * 获取某个二级分类下的所有三级分类
     * @param c2Id
     * @return
     */
    @ApiOperation("获取某个二级分类下的所有三级分类")
    @GetMapping("/getCategory3/{c2id}")
    public Result getCategory3(@PathVariable("c2id") Long c2Id){


        List<BaseCategory3> category3s= baseCategory3Service.getCategory3ByC2Id(c2Id);
        return Result.ok(category3s);
    }
}
