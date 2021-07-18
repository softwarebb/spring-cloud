package com.example.tmy.controller;

import com.example.tmy.rest.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/7/15
 * @Desc
 */
@RestController
public class HiZinkipController {


    @Autowired
    private HelloService helloService;

    @GetMapping("/hi")
    public String restHi(@RequestParam(defaultValue = "eureka") String name) {

        return helloService.hiService("hi/" + name);
    }
}
