package com.atguigu.gmall.product;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.*;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinioTest {


    @Test
    void uploadTest() throws Exception {
        // 1、创建MinioClient
        MinioClient minioClient =
                new MinioClient("http://192.168.200.100:9000",
                        "admin",
                        "admin123456");

        //2、判断 bucket是否存在
        boolean isExist = minioClient.bucketExists("mall-oss");
        if(isExist) {
            System.out.println("Bucket 已经存在,可以直接上传...");
        } else {
            // 3、创建一个名为 mall-oss 的存储桶
            minioClient.makeBucket("mall-oss");
        }

        String path = "C:\\Users\\Administrator\\Desktop\\尚品汇\\资料\\03 商品图片\\4.png";
        //3、使用putObject上传一个文件到存储桶中。

        /**
         * long objectSize,  对象大小
         * long partSize,    部分大小(分片上传) -1L
         */
        FileInputStream stream = new FileInputStream(path);

        PutObjectOptions options = new PutObjectOptions(stream.available(),-1L); //PutObjectOptions //上传参数项
        options.setContentType("image/png"); //指定内容类型
        minioClient.putObject("mall-oss",
                "7.png",
                new FileInputStream(path),
                options);
        System.out.println("上传成功...");
    }
}
