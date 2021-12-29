package com.example.tmy.hystrix.hello;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: mengyuetang@sohu-inc.com
 * @date: 2021/12/16 17:05
 */
@Slf4j
public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("HelloGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        log.info("this is {} observable", name);
        return "hello, " + name + "`s first command.";
    }
}
