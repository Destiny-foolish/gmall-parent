package com.atguigu.gmall.item.service.impl;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lfy
 * @Description
 * @create 2022-12-03 11:44
 */
@Slf4j
@Service
public class SkuDetailServiceImpl implements SkuDetailService {

    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;

    @Autowired
    ThreadPoolExecutor coreExecutor;

    @Override
    public SkuDetailVo getSkuDetailData(Long skuId) {
        //获取商品详情数据
        SkuDetailVo data = new SkuDetailVo();
        //1、异步：商品详情【图片】 //自定义的线程池？
        CompletableFuture<SkuInfo> skuInfoFuture = CompletableFuture.supplyAsync(() -> {
            log.info("详情：skuinfo");
            SkuInfo skuInfo = skuDetailFeignClient.getSkuInfo(skuId).getData();
            return skuInfo;
        },coreExecutor);

        //2、异步：图片
        CompletableFuture<Void> imageFuture = skuInfoFuture.thenAcceptAsync((res) -> {
            log.info("图片：skuimage");
            List<SkuImage> skuImages = skuDetailFeignClient.getSkuImages(skuId).getData(); //1s
            res.setSkuImageList(skuImages);
            data.setSkuInfo(res);
        },coreExecutor);


        //3、异步：当前商品精确完整分类信息； //2s
        CompletableFuture<Void> categoryFuture = skuInfoFuture.thenAcceptAsync(res -> {
            log.info("分类：category");
            CategoryTreeVo categoryTreeVo = skuDetailFeignClient.getCategoryTreeWithC3Id(res.getCategory3Id()).getData();
            //数据模型转换
            CategoryViewDTO viewDTO = convertToCategoryViewDTO(categoryTreeVo);
            data.setCategoryView(viewDTO);
        },coreExecutor);


        //4、异步：实时价格
        CompletableFuture<Void> priceFuture = CompletableFuture.runAsync(() -> {
            log.info("价格：price");
            BigDecimal price = skuDetailFeignClient.getPrice(skuId).getData();
            data.setPrice(price);
        },coreExecutor);



        //5、销售属性;
        CompletableFuture<Void> saleAttrFuture = skuInfoFuture.thenAcceptAsync(res -> {
            log.info("销售属性：saleAttr");
            List<SpuSaleAttr> spuSaleAttrs = skuDetailFeignClient.getSpuSaleAttr(res.getSpuId(), skuId).getData();
            data.setSpuSaleAttrList(spuSaleAttrs);
        },coreExecutor);


        //6、当前sku的所有兄弟们的所有组合可能性。
        CompletableFuture<Void> valueJsonFuture = skuInfoFuture.thenAcceptAsync(res -> {
            log.info("组合：valueJson");
            String json = skuDetailFeignClient.getValuesSkuJson(res.getSpuId()).getData();
            data.setValuesSkuJson(json);
        },coreExecutor);

        //等待所有任务都完成
        CompletableFuture
                .allOf(valueJsonFuture,saleAttrFuture,priceFuture,categoryFuture,imageFuture)
                .join();
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
