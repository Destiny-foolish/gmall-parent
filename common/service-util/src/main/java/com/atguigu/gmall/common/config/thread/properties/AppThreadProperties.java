package com.atguigu.gmall.common.config.thread.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lfy
 * @Description
 * @create 2022-12-05 15:52
 */
@ConfigurationProperties(prefix = "app.threadpool")
@Data
public class AppThreadProperties {

    private Integer corePoolSize;
    private Integer maximumPoolSize;
    private Long keepAliveTime;
    private Integer workQueueSize;

}
