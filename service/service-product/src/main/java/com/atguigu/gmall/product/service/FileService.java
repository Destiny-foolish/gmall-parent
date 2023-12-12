package com.atguigu.gmall.product.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 把文件上传到Minio
     * @param file
     * @return
     */
    String upload(MultipartFile file) throws Exception;
}
