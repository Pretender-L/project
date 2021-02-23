package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class TaskExecutorPoolConfig {

    /**
     * 提交任务
     * 无返回值的任务使用execute(Runnable)
     * 有返回值的任务使用submit(Runnable)
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池数
        executor.setCorePoolSize(5);
        //最大线程数
        executor.setMaxPoolSize(10);
        //队列容量
        executor.setQueueCapacity(100);
        //设置线程名
        executor.setThreadNamePrefix("thread-");
        //当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略
        //拒绝策略:ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程池初始化
        executor.initialize();
        return executor;
    }

}
