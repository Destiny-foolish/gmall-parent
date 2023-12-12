package com.atguigu.gmall.common.config.minio.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "app.minio") //读取配置文件中app.minio下的所有值和JavaBean属性进行绑定
@Data
public class MinioProperties {


    String endpoint;

    String accessKey;

    String secretKey;

    String bucketName;
}
