package com.atguigu.gmall.web.feign;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.vo.CategoryTreeVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lfy
 * @Description
 * @create 2022-12-03 10:35
 */
@RequestMapping("/api/inner/rpc/product")
@FeignClient("service-product") //1、说清楚调用哪个微服务
public interface CategoryFeignClient {

    //给远程的 service-product 发送get请求，路径是
    // /api/inner/rpc/product/category/tree?haha=ddd

    /**
     * 1、先去 注册中心 找到 @FeignClient 注解说明的 service-product 对应的 ip地址
     * 2、给 指定的ip 发送 方法声明的 这种方式请求，并且路径 声明的路径
     * 3、对方处理完成以后，给feign返回json数据。
     * 4、feign把接受到的json数据自动转为 方法指定的类型
     *
     * controller：
     *      @xxxMapping： 接受各种方式的请求
     *      @RequestParam：接受请求参数中的值
     *      @RequestBody：接受请求体中的值
     *      @PathVariable: 接受请求路径的值
     *      @RequestHeader：接受请求头的值
     * feignclient:
     *      @xxxMapping： 发送各种方式的请求
     *      @RequestParam：方法传参的值放到请求参数中发送出去
     *      @RequestBody： 方法传参的值放到请求体中发送出去
     *      @PathVariable: 方法传参的值放到请求路径中发送出去
     *      @RequestHeader：方法传参的值放到请求头中发送出去
     *
     *
     * @return
     */
    @GetMapping("/category/tree")
    Result<List<CategoryTreeVo>> getCategoryTree();



}
