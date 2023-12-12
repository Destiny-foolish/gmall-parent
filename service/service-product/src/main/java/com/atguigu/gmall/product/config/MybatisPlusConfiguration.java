package com.atguigu.gmall.product.config;


import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement //开启基于注解的事务功能
@Configuration
public class MybatisPlusConfiguration {

    @Bean
    MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        //分页拦截器
        PaginationInnerInterceptor pagination = new PaginationInnerInterceptor();
        pagination.setOverflow(true);
        interceptor.addInnerInterceptor(pagination);

        return interceptor;
    }
}
