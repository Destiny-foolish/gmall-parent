package com.atguigu.gmall.product.service;

import com.atguigu.gmall.product.entity.BaseCategory1;
import com.atguigu.gmall.product.vo.CategoryTreeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface BaseCategoryService extends IService<BaseCategory1> {
    List<CategoryTreeVo> getCategoryTree();

    CategoryTreeVo getCategoryTreeWithC3Id(Long c3Id);
}
