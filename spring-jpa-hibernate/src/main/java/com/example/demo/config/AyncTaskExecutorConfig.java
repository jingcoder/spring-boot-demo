package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @Author xu.xiaojing
 * @Date 2018/10/26 15:27
 * @Email xu.xiaojing@frontsurf.com
 * @Description
 *
 * Spring 的异步线程池配置
 */
@Component
@Configuration
@EnableAsync
public class AyncTaskExecutorConfig implements AsyncConfigurer {

    @Value("${async-threadPool-core-pool-size}")
    private Integer corePoolSize;

    @Value("${async-threadPool-max-pool-size}")
    private Integer maxPoolSize;

    @Value("${async-threadPool-queue-capacity}")
    private Integer queueCapacity;



    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setAllowCoreThreadTimeOut(false);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60*60);
        taskExecutor.setThreadNamePrefix("Query-Thread-");
        taskExecutor.initialize();
        System.out.println("init ThreadPoolTaskExecutor");
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncTaskExceptionHandler();
    }

    class AsyncTaskExceptionHandler implements AsyncUncaughtExceptionHandler {
        private final Logger logger = LoggerFactory.getLogger(AsyncTaskExceptionHandler.class);
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
            logger.info("捕获异步线程异常信息，Exception message - " + throwable.getMessage()+" \n Method name - " + method.getName());
            /*System.out.println("捕获线程异常信息");
            logger.info("Exception message - " + throwable.getMessage());
            logger.info("Method name - " + method.getName());
            for (Object param : obj) {
                logger.info("Parameter value - " + param);
            }*/
        }
    }

}
