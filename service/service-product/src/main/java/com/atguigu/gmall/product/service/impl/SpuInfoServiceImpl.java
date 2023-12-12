package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.product.entity.SpuImage;
import com.atguigu.gmall.product.entity.SpuSaleAttr;
import com.atguigu.gmall.product.entity.SpuSaleAttrValue;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.service.SpuSaleAttrValueService;
import com.atguigu.gmall.product.vo.SpuSaveInfoVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.entity.SpuInfo;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.atguigu.gmall.product.mapper.SpuInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Administrator
 * @description 针对表【spu_info(商品表)】的数据库操作Service实现
 * @createDate 2022-11-29 11:42:45
 */
@Slf4j
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper, SpuInfo>
        implements SpuInfoService {


    @Autowired
    SpuImageService spuImageService;

    @Autowired
    SpuSaleAttrService spuSaleAttrService;

    @Autowired
    SpuSaleAttrValueService spuSaleAttrValueService;

    @Transactional
    @Override
    public void saveSpuInfoData(SpuSaveInfoVo vo) {
        log.info("保存spu： {}", vo);
        //1、存 spu_info
        SpuInfo spuInfo = new SpuInfo();
        BeanUtils.copyProperties(vo, spuInfo);
        save(spuInfo);
        Long spuId = spuInfo.getId();

        //2、存图片

//        List<SpuSaveInfoVo.SpuImageListDTO> spuImageList = vo.getSpuImageList();
//        List<SpuImage> images = new ArrayList<>();
//        for (SpuSaveInfoVo.SpuImageListDTO dto : spuImageList) {
//            SpuImage spuImage = new SpuImage();
//            //先复制前端的数据
//            BeanUtils.copyProperties(dto, spuImage);
//            //回填spuId
//            spuImage.setSpuId(spuId);
//            images.add(spuImage);
//        }
        //StreamAPI
        List<SpuImage> spuImages = vo.getSpuImageList()
                .stream()
                .map(item -> {
                    SpuImage spuImage = new SpuImage();
                    BeanUtils.copyProperties(item, spuImage);
                    spuImage.setSpuId(spuId);
                    return spuImage;
                }).collect(Collectors.toList());
        spuImageService.saveBatch(spuImages);

        //3、存 销售属性名 spu_sale_attr
        List<SpuSaleAttr> saleAttrs = vo.getSpuSaleAttrList()
                .stream()
                .map(item -> {
                    SpuSaleAttr saleAttr = new SpuSaleAttr();
                    BeanUtils.copyProperties(item, saleAttr);
                    saleAttr.setSpuId(spuId);
                    return saleAttr;
                }).collect(Collectors.toList());
        spuSaleAttrService.saveBatch(saleAttrs);

        //4、存 销售属性值  spu_sale_attr_value
        List<SpuSaleAttrValue> attrValues = vo.getSpuSaleAttrList()
                .stream()
                .flatMap(item -> {
                    //item是每个销售属性，带了很多销售属性值
                    return item.getSpuSaleAttrValueList()
                            .stream()
                            .map(val -> {
                                SpuSaleAttrValue attrValue = new SpuSaleAttrValue();
                                attrValue.setSpuId(spuId);
                                attrValue.setBaseSaleAttrId(val.getBaseSaleAttrId());
                                attrValue.setSaleAttrValueName(val.getSaleAttrValueName());
                                attrValue.setSaleAttrName(item.getSaleAttrName());
                                return attrValue;
                            });
                }).collect(Collectors.toList());
        spuSaleAttrValueService.saveBatch(attrValues);

    }


//    public static void main(String[] args) {
//        System.out.println("主线程：" + Thread.currentThread());
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
//        //1、把每个元素 * 2  声明式编程：  命令式编程
////        Integer integer = list.stream()
////                .map(item -> {
////                    System.out.println(Thread.currentThread() + "正在处理：[" + item + "]");
////                    if (item == 2) {
////                        try {
////                            Thread.sleep(2000);
////                        } catch (InterruptedException e) {
////                            e.printStackTrace();
////                        }
////                    }
////                    return item * 2;
////                })
////                .filter(item -> item % 3 == 0)
////                .//打散：拉平
////                .reduce((o1, o2) -> o1 + o2)
////                .get();
//
//        List<Integer> collect = list.stream()
//                .flatMap(item -> Arrays.asList(item + 6, item + 8).stream())
//                .collect(Collectors.toList());
//        System.out.println(collect);
//
//        //2、把所有能被3整除的都挑出来
//
//    }
}




