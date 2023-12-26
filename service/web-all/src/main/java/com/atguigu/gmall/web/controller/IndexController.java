package com.atguigu.gmall.web.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.vo.CategoryTreeVo;
import com.atguigu.gmall.web.feign.CategoryFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lfy
 * @Description 首页
 * @create 2022-12-03 9:35
 */
@Controller
public class IndexController {


    @Autowired
    CategoryFeignClient categoryFeignClient;

    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/")
    public String index(Model model){
        //远程调用 service-product 获取系统所有的三级分类数据
        Result<List<CategoryTreeVo>> categorys = categoryFeignClient.getCategoryTree();
        List<CategoryTreeVo> data = categorys.getData();
        model.addAttribute("list",data);
        return "index/index";
    }

}
