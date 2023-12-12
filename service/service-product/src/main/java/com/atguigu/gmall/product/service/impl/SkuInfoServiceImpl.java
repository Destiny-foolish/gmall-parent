package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.product.entity.SkuAttrValue;
import com.atguigu.gmall.product.entity.SkuImage;
import com.atguigu.gmall.product.entity.SkuSaleAttrValue;
import com.atguigu.gmall.product.service.SkuAttrValueService;
import com.atguigu.gmall.product.service.SkuImageService;
import com.atguigu.gmall.product.service.SkuSaleAttrValueService;
import com.atguigu.gmall.product.vo.SkuSaveVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.entity.SkuInfo;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @description 针对表【sku_info(库存单元表)】的数据库操作Service实现
 * @createDate 2022-11-29 11:42:45
 */
@Slf4j
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo>
        implements SkuInfoService {

    @Autowired
    SkuImageService skuImageService;

    @Autowired
    SkuAttrValueService attrValueService;

    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;


    @Transactional
    @Override
    public void saveSkuInfoData(SkuSaveVo vo) {
        log.info("sku保存： {}", vo);
        //1、 把基本信息保存到 sku_info
        SkuInfo skuInfo = new SkuInfo();
        BeanUtils.copyProperties(vo, skuInfo);
        save(skuInfo);

        //提取到刚保存的sku的自增id
        Long skuId = skuInfo.getId();

        //2、图片信息： sku_image
        List<SkuImage> skuImages = vo.getSkuImageList()
                .stream()
                .map(item -> {
                    SkuImage image = new SkuImage();
                    BeanUtils.copyProperties(item,image);
                    image.setSkuId(skuId);
                    return image;
                }).collect(Collectors.toList());
        skuImageService.saveBatch(skuImages);

        //3、平台属性：  sku_attr_value
        List<SkuAttrValue> attrValues = vo.getSkuAttrValueList()
                .stream()
                .map(item -> {
                    SkuAttrValue value = new SkuAttrValue();
                    BeanUtils.copyProperties(item,value);
                    value.setSkuId(skuId);
                    return value;
                }).collect(Collectors.toList());
        attrValueService.saveBatch(attrValues);

        //4、销售属性： sku_sale_attr_value
        List<SkuSaleAttrValue> saleAttrValues = vo.getSkuSaleAttrValueList()
                .stream()
                .map(item -> {
                    SkuSaleAttrValue attrValue = new SkuSaleAttrValue();

                    attrValue.setSkuId(skuId);
                    attrValue.setSpuId(skuInfo.getSpuId());
                    attrValue.setSaleAttrValueId(item.getSaleAttrValueId());

                    return attrValue;
                }).collect(Collectors.toList());
        skuSaleAttrValueService.saveBatch(saleAttrValues);

    }
}




