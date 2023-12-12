package com.atguigu.gmall.product.controller;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.entity.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "品牌管理")
@RestController
@RequestMapping("/admin/product")
public class BaseTrademarkController {


    @Autowired
    BaseTrademarkService baseTrademarkService;

    @ApiOperation("查询所有品牌")
    @GetMapping("/baseTrademark/getTrademarkList")
    public Result getTrademarkList(){

        List<BaseTrademark> list = baseTrademarkService.list();
        return Result.ok(list);
    }


    @ApiOperation("分页获取品牌列表")
    @GetMapping("/baseTrademark/{pn}/{ps}")
    public Result getTrademark(@PathVariable("pn") Long pn,
                               @PathVariable("ps") Long ps){
        Page<BaseTrademark> page = new Page<>(pn,ps);

        //分页信息、查到的结果
        Page<BaseTrademark> result = baseTrademarkService.page(page);

        return Result.ok(result);
    }

    @ApiOperation("保存品牌")
    @PostMapping("/baseTrademark/save")
    public Result save(@RequestBody BaseTrademark baseTrademark){

        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }

    @ApiOperation("删除品牌")
    @DeleteMapping("/baseTrademark/remove/{id}")
    public Result delete(@PathVariable("id") Long id){
        baseTrademarkService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("查询品牌")
    @GetMapping("/baseTrademark/get/{id}")
    public Result get(@PathVariable("id") Long id){
        BaseTrademark trademark = baseTrademarkService.getById(id);
        return Result.ok(trademark);
    }

    @ApiOperation("修改品牌")
    @PutMapping("/baseTrademark/update")
    public Result update(@RequestBody BaseTrademark baseTrademark){
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }
}
