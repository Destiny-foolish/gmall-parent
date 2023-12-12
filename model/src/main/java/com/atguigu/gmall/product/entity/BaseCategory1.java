package com.atguigu.gmall.product.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("base_category1")
public class BaseCategory1 {

    @TableId(type = IdType.AUTO)
    private Long id; //主键

    @TableField("name")
    private String name; //分类名

}
