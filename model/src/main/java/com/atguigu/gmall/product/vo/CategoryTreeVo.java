package com.atguigu.gmall.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @author lfy
 * @Description
 * @create 2022-12-03 9:43
 */
@Data
public class CategoryTreeVo {
    private Long categoryId; //分类id
    private String categoryName; //分类名字
    private List<CategoryTreeVo> categoryChild;//子分类
}
