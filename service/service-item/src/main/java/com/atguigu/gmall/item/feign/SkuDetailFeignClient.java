package com.atguigu.gmall.item.feign;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.entity.SkuImage;
import com.atguigu.gmall.product.entity.SkuInfo;
import com.atguigu.gmall.product.entity.SpuSaleAttr;
import com.atguigu.gmall.product.vo.CategoryTreeVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author lfy
 * @Description
 * @create 2022-12-03 11:55
 */
@RequestMapping("/api/inner/rpc/product")
@FeignClient("service-product")
public interface SkuDetailFeignClient {

    /**
     * 1、根据三级分类id，得到整个分类的完整路径
     * @param c3Id
     * @return
     */
    @GetMapping("/category/view/{c3Id}")
    Result<CategoryTreeVo> getCategoryTreeWithC3Id(@PathVariable("c3Id") Long c3Id);

    /**
     * 2、获取sku-info
     * @param skuId
     * @return
     */
    @GetMapping("/skuinfo/{skuId}")
    public Result<SkuInfo> getSkuInfo(@PathVariable("skuId") Long skuId);

    /**
     * 3、获取sku-images
     * @param skuId
     * @return
     */
    @GetMapping("/skuimages/{skuId}")
    public Result<List<SkuImage>> getSkuImages(@PathVariable("skuId") Long skuId);

    /**
     * 4、获取 实时价格
     * @param skuId
     * @return
     */
    @GetMapping("/skuprice/{skuId}")
    public Result<BigDecimal> getPrice(@PathVariable("skuId") Long skuId);

    /**
     * 5、获取 spu销售属性名和值 集合
     * @param spuId
     * @return
     */
    @GetMapping("/skusaleattr/{spuId}/{skuId}")
    public Result<List<SpuSaleAttr>> getSpuSaleAttr(@PathVariable("spuId") Long spuId,
                                                    @PathVariable("skuId") Long skuId);

    /**
     * 6、获取 valuesSkuJson
     * @param spuId
     * @return
     */
    @GetMapping("/valuesSkuJson/{spuId}")
    Result<String> getValuesSkuJson(@PathVariable("spuId") Long spuId);




}
