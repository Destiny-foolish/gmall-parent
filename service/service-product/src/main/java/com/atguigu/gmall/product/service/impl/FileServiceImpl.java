package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.util.DateUtil;
import com.atguigu.gmall.common.config.minio.properties.MinioProperties;
import com.atguigu.gmall.product.service.FileService;
import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {


    @Autowired
    MinioProperties minioProperties;

    @Autowired
    MinioClient client;

    @Override
    public String upload(MultipartFile file) throws Exception {
        //1、创建MinioClient
//        MinioClient client = new MinioClient(minioProperties.getEndpoint(),
//                minioProperties.getAccessKey(),
//                minioProperties.getSecretKey());

        //2、判断 bucket
//        boolean exists = client.bucketExists(minioProperties.getBucketName());
//        if (!exists) {
//            client.makeBucket(minioProperties.getBucketName());
//        }

        //3、上传文件； 加上唯一前缀
        String date = DateUtil.formatDate(new Date());
        String filename =  date+"/"+UUID.randomUUID().toString() +"_"+ file.getOriginalFilename();
        InputStream stream = file.getInputStream();
        PutObjectOptions options = new PutObjectOptions(file.getSize(),-1L);
        options.setContentType(file.getContentType());
        //上传
        client.putObject(minioProperties.getBucketName(),filename,stream,options);

        //4、返回文件的访问地址
        String url = minioProperties.getEndpoint()+"/"+minioProperties.getBucketName()+"/"+filename;

        //优化：
        //1）、文件名不重复
        //2）、以当天时间作为文件夹进行组织

        return url;
    }
}
