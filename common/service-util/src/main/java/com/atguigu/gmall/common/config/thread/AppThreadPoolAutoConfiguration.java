package com.atguigu.gmall.common.config.thread;

import com.atguigu.gmall.common.config.thread.properties.AppThreadProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author lfy
 * @Description
 * @create 2022-12-05 15:25
 */
@EnableConfigurationProperties({AppThreadProperties.class})
@Slf4j
@Configuration
public class AppThreadPoolAutoConfiguration {

    /**
     * int corePoolSize,     核心线程数
     * int maximumPoolSize,  最大线程数； 阻塞队列慢开到最大。 max-core：弹性线程
     * long keepAliveTime,   弹性线程的存活时间。 多久不干活就释放掉
     * TimeUnit unit,        时间单位
     * BlockingQueue<Runnable> workQueue, 阻塞队列。
     *          任务过来核心线程进行处理，核心都忙的情况，新任务进入阻塞队列。
     * ThreadFactory threadFactory,   线程工厂： 创建新线程。 new Thread()
     * RejectedExecutionHandler handler  拒绝策略：
     *          核心、最大、队列都满了，新来的任务启用 拒绝策略
     * 队列大小：
     * 1）、压测：  峰值*1.5；
     * 2）、内存：  未来微服务部署到哪种机器。
     *
     * @return
     */
    @Bean
    public ThreadPoolExecutor coreExecutor(AppThreadProperties threadProperties) {
        return new ThreadPoolExecutor(threadProperties.getCorePoolSize(),
                threadProperties.getMaximumPoolSize(),
                threadProperties.getKeepAliveTime(),
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(threadProperties.getWorkQueueSize()),
                new ThreadFactory() {
                    int i = 1;
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r); //创建线程，执行任务
                        //给名字
                        thread.setName("核心线程["+ i++ +"]");
                        //1~10
                        thread.setPriority(10); //设置优先级
                        return thread;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

//    @Bean  //关闭非核心线程池.  线程池任务隔离
//    public ThreadPoolExecutor otherExecutor() {
//        return new ThreadPoolExecutor(8,
//                24,
//                5,
//                TimeUnit.MINUTES,
//                new LinkedBlockingQueue<>(3000),
//                new ThreadFactory() {
//                    int i = 1;
//                    @Override
//                    public Thread newThread(Runnable r) {
//                        log.info("线程池：准备新线程; 老线程会复用");
//                        Thread thread = new Thread(r); //创建线程，执行任务
//                        //给名字
//                        thread.setName("线程池：核心线程：" + i++);
//                        thread.setPriority();
//                        return thread;
//                    }
//                }
//        );
//    }
}
