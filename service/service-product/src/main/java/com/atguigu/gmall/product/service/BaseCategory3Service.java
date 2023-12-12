package com.atguigu.gmall.product.service;

import com.atguigu.gmall.product.entity.BaseCategory3;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【base_category3(三级分类表)】的数据库操作Service
* @createDate 2022-11-29 11:42:45
*/
public interface BaseCategory3Service extends IService<BaseCategory3> {

    /**
     * 获取某个二级分类下的所有三级分类
     * @param c2Id
     * @return
     */
    List<BaseCategory3> getCategory3ByC2Id(Long c2Id);

}
