package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.product.entity.BaseCategory1;
import com.atguigu.gmall.product.mapper.BaseCategory2Mapper;
import com.atguigu.gmall.product.mapper.BaseCategoryMapper;
import com.atguigu.gmall.product.service.BaseCategoryService;
import com.atguigu.gmall.product.vo.CategoryTreeVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseCategoryServiceImpl extends ServiceImpl<BaseCategoryMapper, BaseCategory1>
        implements BaseCategoryService {
    @Autowired
    BaseCategory2Mapper baseCategory2Mapper;

    @Override
    public List<CategoryTreeVo> getCategoryTree() {

        List<CategoryTreeVo> vos = baseCategory2Mapper.getCategoryTree();
        return vos;
    }

    @Override
    public CategoryTreeVo getCategoryTreeWithC3Id(Long c3Id) {
        CategoryTreeVo vo = baseCategory2Mapper.getCategoryTreeWithC3Id(c3Id);
        return vo;
    }
}
