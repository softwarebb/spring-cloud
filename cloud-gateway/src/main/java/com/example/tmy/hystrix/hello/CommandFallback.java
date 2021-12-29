package com.example.tmy.hystrix.hello;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.exception.HystrixBadRequestException;
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
public class CommandFallback extends HystrixCommand<String> {

    private final String name;

    public CommandFallback(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("HelloGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        // 普通业务异常
//        throw  new Exception(name + " and do fail");
        // 不fallback的异常
//        throw  new HystrixBadRequestException(name + " and do fail");
        //超时
        int count = 0;
        while (count >= 0) {
            count++;
        }
        return name;
    }

    @Override
    protected String getFallback() {
        return name + " and get fallback";
    }
}
