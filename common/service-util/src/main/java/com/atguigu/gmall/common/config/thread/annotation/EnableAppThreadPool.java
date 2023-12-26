package com.atguigu.gmall.common.config.thread.annotation;


import com.atguigu.gmall.common.config.minio.MinioAutoConfiguration;
import com.atguigu.gmall.common.config.thread.AppThreadPoolAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AppThreadPoolAutoConfiguration.class})
public @interface EnableAppThreadPool {
}
