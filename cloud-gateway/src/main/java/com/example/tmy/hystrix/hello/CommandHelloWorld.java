package com.example.tmy.hystrix.hello;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @description: desc
 * @author: mengyuetang
 * @email: mengyuetang@sohu-inc.com
 * @date: 2021/12/16 17:05
 */
public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("HelloGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "hello, " + name + "`s first command.";
    }
}
