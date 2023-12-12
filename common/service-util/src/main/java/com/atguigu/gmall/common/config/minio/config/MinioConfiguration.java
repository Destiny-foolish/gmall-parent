package com.atguigu.gmall.common.config.minio.config;


import com.atguigu.gmall.common.config.minio.properties.MinioProperties;
import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfiguration {

    //SpringBoot给容器中放一个组件。 所有参数就是默认从容器中获取
    @Bean
    public MinioClient minioClient(MinioProperties minioProperties) throws  Exception {
        //1、创建MinioClient
        MinioClient client = new MinioClient(minioProperties.getEndpoint(),
                minioProperties.getAccessKey(),
                minioProperties.getSecretKey());

        //2、判断是否有指定的bucket
        boolean exists = client.bucketExists(minioProperties.getBucketName());
        if (!exists) {
            client.makeBucket(minioProperties.getBucketName());
        }
        return client;
    }
}
