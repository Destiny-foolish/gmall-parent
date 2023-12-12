package com.atguigu.gmall.product.controller;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.service.FileService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@Slf4j
@Api(tags = "文件管理")
@RequestMapping("/admin/product")
@RestController
public class FileController {


    @Autowired
    FileService fileService;

    /**
     * 文件上传：
     * 多部件上传
     * web开发：
     *   请求：
     *      请求首行（请求方式、请求地址、协议）
     *              @PathVariable：获取路径位置的变量
     *              @RequestParam：查询字符串或请求体
     *      请求头
     *              @ReuestHeader：请求头
     *              @CookieValue：获取cookie值
     *      请求体
     *              @RequestBody： 获取请求体中的所有数据
     *              @RequestPart： 获取请求体中的文件项
     *   响应：
     *      响应首行（响应状态码）
     *      响应头
     *      响应体
     *  完整的请求地址：
     *      http://api.gmall.com:80/admin/product/xxx?k=v&k=v
     *      【协议://主机:端口/路径?查询字符串
     *        请求体数据k=v】
     *        请求参数：带到url?后面，也可以带到请求体中。
     *
     * @return
     */
    @PostMapping("/fileUpload")
    public Result upload(@RequestPart("file")MultipartFile file) throws Exception {

        String contentType = file.getContentType();
        String filename = file.getOriginalFilename();
        // <input name="haha" type="file">
//        String name = file.getName(); //文件上传框的名，不是文件名。
        log.info("文件上传：文件名：{}，文件大小：{}，文件类型：{}",filename,file.getSize(),contentType);

        String path = fileService.upload(file);
        //TODO 把这个文件上传给Minio，并返回访问地址
        return Result.ok(path);
    }
}
