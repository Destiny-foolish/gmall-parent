package com.atguigu.gmall.product;

import com.atguigu.gmall.common.config.Swagger2Config;
import com.atguigu.gmall.common.config.minio.MinioAutoConfiguration;
import com.atguigu.gmall.common.config.minio.annotation.EnableMinio;
import com.atguigu.gmall.common.config.minio.config.MinioConfiguration;
import com.atguigu.gmall.common.config.minio.properties.MinioProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;


//@EnableCircuitBreaker
//@EnableDiscoveryClient
//@SpringBootApplication
//启动只扫描主程序所在的包以及子包
//主程序：   com.atguigu.gmall.product
//swagger： com.atguigu.gmall.common.config
//minio:    com.atguigu.gmall.common.config.minio

@Import({Swagger2Config.class})
@EnableMinio //开启minio功能
@MapperScan(basePackages = "com.atguigu.gmall.product.mapper")
@SpringCloudApplication //这是一个cloud服务，自动开始服务发现，服务熔断等功能
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }
}
