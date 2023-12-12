package com.atguigu.gmall.product.vo;

import com.atguigu.gmall.product.entity.SkuInfo;
import com.atguigu.gmall.product.entity.SpuSaleAttr;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lfy
 * @Description 商品详情数据
 * @create 2022-12-03 11:34
 */
@Data
public class SkuDetailVo {
    //1、分类信息
    private CategoryViewDTO categoryView;

    //2、sku信息
    private SkuInfo skuInfo;

    //3、实时价格
    private BigDecimal price;

    //4、销售属性集合
    private List<SpuSaleAttr> spuSaleAttrList;

    //5、valuesSkuJson
    private String valuesSkuJson;



    @Data
    public static class CategoryViewDTO {
        private Long category1Id;
        private Long category2Id;
        private Long category3Id;
        private String category1Name;
        private String category2Name;
        private String category3Name;

    }
}
