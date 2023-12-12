package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.product.entity.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.entity.BaseAttrInfo;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Administrator
* @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
* @createDate 2022-11-29 11:42:45
*/
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
    implements BaseAttrInfoService{

    @Autowired
    BaseAttrValueService baseAttrValueService;

    @Override
    public List<BaseAttrInfo> getBaseAttrAndValue(Long category1Id,
                                                  Long category2Id,
                                                  Long category3Id) {

        return baseMapper.getBaseAttrAndValue(category1Id,category2Id,category3Id);
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        //1、把属性名信息保存到 base_attr_info
        boolean save = save(baseAttrInfo);
        //得到上一步保存到属性的自增id
        Long attrId = baseAttrInfo.getId();


        //2、 把属性值信息保存到 base_attr_value
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        for (BaseAttrValue attrValue : attrValueList) {
            //回填属性id
            attrValue.setAttrId(attrId);
        }
        baseAttrValueService.saveBatch(attrValueList);
    }

    @Override
    public void updateAttrInfo(BaseAttrInfo baseAttrInfo) {
        //1、修改 base_attr_info
        updateById(baseAttrInfo);


        List<BaseAttrValue> valueList = baseAttrInfo.getAttrValueList();

        //哪些是删除，前端没提交的就是要删除。
        //数据库原来：59,60,61,62,63
        //前端提交的：59,60,63
        //差集：把 61,62 删除
        // delete from base_attr_value where attr_id = 11 and id not in(59,60,63)
        List<Long> ids = new ArrayList<>(); //前端提交的所有属性值id
        for (BaseAttrValue attrValue : valueList) {
            if (attrValue.getId() != null) {
                ids.add(attrValue.getId());
            }
        }
        if(ids.size() > 0){
            baseAttrValueService
                    .lambdaUpdate() //修改、删除
                    .eq(BaseAttrValue::getAttrId, baseAttrInfo.getId())
                    .notIn(BaseAttrValue::getId, ids)
                    .remove();
        }else {
            baseAttrValueService.lambdaUpdate()
                    .eq(BaseAttrValue::getAttrId, baseAttrInfo.getId())
                    .remove();
        }

//        LambdaQueryChainWrapper<BaseAttrValue> wrapper = baseAttrValueService
//                .lambdaQuery()
//                .eq(BaseAttrValue::getAttrId, baseAttrInfo.getId())
//                .notIn(BaseAttrValue::getId, ids); //写法不让用？？？

//        LambdaQueryWrapper<BaseAttrValue> wrapper =
//                new LambdaQueryWrapper<BaseAttrValue>()
//                .eq(BaseAttrValue::getAttrId, baseAttrInfo.getId())
//                .notIn(BaseAttrValue::getId, ids);
//        //删除前端没提交的id集合  lambdaUpdate()/lambdaQuery()：快速得到queryWrapper
//        baseAttrValueService.remove(wrapper);

        //用更简单的写法


//        baseAttrValueService
//                .lambdaQuery()  //查询
//                .eq(BaseAttrValue::getAttrId, 11L)
//                .select(BaseAttrValue::getAttrId)
//                .list();//查询


        //2、修改 base_attr_value
        for (BaseAttrValue attrValue : valueList) {
            if (attrValue.getId() == null) {
                //这些是新增，属性值没提交id的就是新增
                //回填attrId
                attrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueService.save(attrValue);
            }else {
                //这些是修改，属性值提交id就是修改
                baseAttrValueService.updateById(attrValue);
            }
        }



    }
}




