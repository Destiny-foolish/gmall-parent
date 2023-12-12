package com.atguigu.gmall.item.service.impl;
import java.math.BigDecimal;
import java.util.List;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.feign.SkuDetailFeignClient;
import com.atguigu.gmall.product.entity.SkuImage;
import com.atguigu.gmall.product.entity.SpuSaleAttr;
import com.atguigu.gmall.product.vo.CategoryTreeVo;
import com.google.common.collect.Lists;
import com.atguigu.gmall.product.entity.SkuInfo;
import com.atguigu.gmall.product.vo.SkuDetailVo.CategoryViewDTO;

import com.atguigu.gmall.item.service.SkuDetailService;
import com.atguigu.gmall.product.vo.SkuDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lfy
 * @Description
 * @create 2022-12-03 11:44
 */
@Service
public class SkuDetailServiceImpl implements SkuDetailService {

    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;

    @Override
    public SkuDetailVo getSkuDetailData(Long skuId) {
        //TODO 获取商品详情数据
        SkuDetailVo data = new SkuDetailVo();
        //1、商品详情【图片】
        SkuInfo skuInfo = skuDetailFeignClient.getSkuInfo(skuId).getData();
        List<SkuImage> skuImages = skuDetailFeignClient.getSkuImages(skuId).getData();
        skuInfo.setSkuImageList(skuImages);
        data.setSkuInfo(skuInfo);


        //2、当前商品精确完整分类信息；
        CategoryTreeVo categoryTreeVo = skuDetailFeignClient.getCategoryTreeWithC3Id(skuInfo.getCategory3Id()).getData();
        //数据模型转换
        CategoryViewDTO viewDTO = convertToCategoryViewDTO(categoryTreeVo);
        data.setCategoryView(viewDTO);

        //3、实时价格
        BigDecimal price = skuDetailFeignClient.getPrice(skuId).getData();
        data.setPrice(price);

        //4、销售属性;
        List<SpuSaleAttr> spuSaleAttrs = skuDetailFeignClient.getSpuSaleAttr(skuInfo.getSpuId(),skuId).getData();
        data.setSpuSaleAttrList(spuSaleAttrs);

        //5、当前sku的所有兄弟们的所有组合可能性。
        String json = skuDetailFeignClient.getValuesSkuJson(skuInfo.getSpuId()).getData();
        data.setValuesSkuJson(json);


        return data;
    }

    private CategoryViewDTO convertToCategoryViewDTO(CategoryTreeVo categoryTreeVo) {
        CategoryViewDTO viewDTO = new CategoryViewDTO();
        viewDTO.setCategory1Id(categoryTreeVo.getCategoryId());
        viewDTO.setCategory1Name(categoryTreeVo.getCategoryName());

        CategoryTreeVo child1 = categoryTreeVo.getCategoryChild().get(0);
        viewDTO.setCategory2Id(child1.getCategoryId());
        viewDTO.setCategory2Name(child1.getCategoryName());

        CategoryTreeVo child2 = child1.getCategoryChild().get(0);
        viewDTO.setCategory3Id(child2.getCategoryId());
        viewDTO.setCategory3Name(child2.getCategoryName());
        return viewDTO;
    }
}
