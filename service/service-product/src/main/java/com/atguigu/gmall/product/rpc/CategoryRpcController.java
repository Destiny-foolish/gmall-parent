package com.atguigu.gmall.product.rpc;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.service.BaseCategoryService;
import com.atguigu.gmall.product.vo.CategoryTreeVo;
import com.atguigu.gmall.product.vo.SkuDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lfy
 * @Description
 * @create 2022-12-03 10:06
 */
@RequestMapping("/api/inner/rpc/product")
@RestController
public class CategoryRpcController {


    @Autowired
    BaseCategoryService baseCategoryService;


    /**
     * 获取分类的全部数据并组织成树形结构
     * @return
     */
    @GetMapping("/category/tree")
    public Result<List<CategoryTreeVo>> getCategoryTree(){

        List<CategoryTreeVo> vos = baseCategoryService.getCategoryTree();
        return Result.ok(vos);
    }


    /**
     * 根据三级分类id，得到整个分类的完整路径
     * @param c3Id
     * @return
     */
    @GetMapping("/category/view/{c3Id}")
    public Result<CategoryTreeVo> getCategoryTreeWithC3Id(@PathVariable("c3Id") Long c3Id){
        CategoryTreeVo vo = baseCategoryService.getCategoryTreeWithC3Id(c3Id);

        return Result.ok(vo);
    }
}
