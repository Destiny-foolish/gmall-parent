package com.atguigu.gmall.product.service;

import com.atguigu.gmall.product.entity.SkuInfo;
import com.atguigu.gmall.product.vo.SkuSaveVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【sku_info(库存单元表)】的数据库操作Service
* @createDate 2022-11-29 11:42:45
*/
public interface SkuInfoService extends IService<SkuInfo> {

    void saveSkuInfoData(SkuSaveVo vo);
}
