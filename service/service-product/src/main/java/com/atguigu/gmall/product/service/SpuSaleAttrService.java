package com.atguigu.gmall.product.service;

import com.atguigu.gmall.product.entity.SpuSaleAttr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Service
* @createDate 2022-11-29 11:42:45
*/
public interface SpuSaleAttrService extends IService<SpuSaleAttr> {

    List<SpuSaleAttr> getSpuSaleAttrList(Long spuId);

    List<SpuSaleAttr> getSpuSaleAttrListOrder(Long spuId,Long skuId);

    /**
     * 根据spuid得到所有sku的销售属性组合
     * @param spuId
     * @return
     */
    String getValuesSkuJson(Long spuId);
}
