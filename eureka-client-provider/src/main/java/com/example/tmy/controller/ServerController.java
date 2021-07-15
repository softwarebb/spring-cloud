package com.example.tmy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/7/15
 * @Desc
 */
@RestController("/provider")
@Slf4j
public class ServerController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/hi")
    public String sayHi( String name) {
        return "I say " + name + ",you say hi，from port=" + port;
    }
}
