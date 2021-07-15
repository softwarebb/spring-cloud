package com.example.tmy.controller;

import com.example.tmy.service.feign.HelloFeignService;
import com.example.tmy.service.rest.HelloService;
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
@RestController("/consumer")
public class RobbinController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private HelloFeignService helloFeignService;

    @GetMapping("rest/hi")
    public String restHi(@RequestParam(defaultValue = "eureka") String name) {
        return helloService.hiService("rest/hi/" + name);
    }


    @GetMapping("feign/hi")
    public String feignHi(@RequestParam(defaultValue = "eureka") String name) {
        return helloFeignService.hiService("feign/hi/" + name);
    }

}
