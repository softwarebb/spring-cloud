package com.example.tmy.hystrix.hello;

import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: mengyuetang@sohu-inc.com
 * @date: 2021/12/16 17:05
 */

/**
 * 四种情况触发fallback
 * 1 run/construct方法抛出非HystricBadRequestException异常
 * 2 run/construct方法调用超时
 * 3 熔断器开启拦截调用
 * 4 线程池/队列/信号量是否跑满
 */
@Slf4j
public class CommandFallbackWithCircleCutBreaker extends HystrixCommand<String> {

    private final String name;

    public CommandFallbackWithCircleCutBreaker(String name) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CircleCutBreakerGroup"))
                        .andCommandKey(HystrixCommandKey.Factory.asKey("CircleCutBreakerKey"))
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("CircleCutBreakerPool"))
                        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(200))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter().withCircuitBreakerEnabled(true)
                                        .withCircuitBreakerRequestVolumeThreshold(3) // 请求阈值，在十秒内请求数大于3个时启动熔断器
//                        .withCircuitBreakerErrorThresholdPercentage(80)
//                        .withCircuitBreakerForceOpen(true)
//                        .withCircuitBreakerForceClosed(false)
//                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
//                        .withExecutionTimeoutInMilliseconds(5000)
                        )
        );
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        // 普通业务异常
//        throw  new Exception(name + " and do fail");
        // 不fallback的异常
//        throw  new HystrixBadRequestException(name + " and do fail");
        //超时
        int num = Integer.valueOf(name);
        if (num % 2 == 0 && num < 10) {
            return name;
        } else {
            int count = 0;
            while (count >= 0) {
                count++;
            }
        }
        return name;
    }

    @Override
    protected String getFallback() {
        return "CircuitBreaker failback: " + name;
    }
}
