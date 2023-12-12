package com.atguigu.gmall.item.service;

import com.atguigu.gmall.product.vo.SkuDetailVo;

/**
 * @author lfy
 * @Description
 * @create 2022-12-03 11:44
 */
public interface SkuDetailService {

    /**
     * 获取商品详情数据
     * @param skuId
     * @return
     */
    SkuDetailVo getSkuDetailData(Long skuId);
}
