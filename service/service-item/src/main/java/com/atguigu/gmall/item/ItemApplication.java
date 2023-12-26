package com.atguigu.gmall.item;

import com.atguigu.gmall.common.config.thread.annotation.EnableAppThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lfy
 * @Description
 * @create 2022-12-03 11:20
 */
@EnableAppThreadPool
@EnableFeignClients
@SpringCloudApplication
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class,args);
    }
}
