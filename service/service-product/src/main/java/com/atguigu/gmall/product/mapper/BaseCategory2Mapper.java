package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.product.entity.BaseCategory2;
import com.atguigu.gmall.product.vo.CategoryTreeVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【base_category2(二级分类表)】的数据库操作Mapper
* @createDate 2022-11-29 11:42:46
* @Entity com.atguigu.gmall.product.entity.BaseCategory2
*/
//@Mapper
public interface BaseCategory2Mapper extends BaseMapper<BaseCategory2> {

    List<CategoryTreeVo> getCategoryTree();

    CategoryTreeVo getCategoryTreeWithC3Id(@Param("c3Id") Long c3Id);
}




