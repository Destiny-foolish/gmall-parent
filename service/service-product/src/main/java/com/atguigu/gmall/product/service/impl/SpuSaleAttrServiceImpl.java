package com.atguigu.gmall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.product.vo.ValueSkuJsonVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.entity.SpuSaleAttr;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author Administrator
* @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Service实现
* @createDate 2022-11-29 11:42:45
*/
@Slf4j
@Service
public class SpuSaleAttrServiceImpl extends ServiceImpl<SpuSaleAttrMapper, SpuSaleAttr>
    implements SpuSaleAttrService{

    /**
     * 查询spu销售属性名和值的集合
     * @param spuId
     * @return
     */
    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(Long spuId) {
        List<SpuSaleAttr> saleAttrs =  baseMapper.getSpuSaleAttrList(spuId);
        return saleAttrs;
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrListOrder(Long spuId,Long skuId) {
        List<SpuSaleAttr> saleAttrs = baseMapper.getSpuSaleAttrListOrder(spuId,skuId);

        return saleAttrs;
    }

    @Override
    public String getValuesSkuJson(Long spuId) {
        List<ValueSkuJsonVo> vos = baseMapper.getValuesSkuJson(spuId);

        Map<String, Long> map = vos.stream()
                .collect(Collectors.toMap((t) -> t.getValueJson(), t -> t.getSkuId()));
        String jsonString = JSON.toJSONString(map);
        log.info("兄弟们的销售属性组合： {}",jsonString);
        return jsonString;
    }
}




