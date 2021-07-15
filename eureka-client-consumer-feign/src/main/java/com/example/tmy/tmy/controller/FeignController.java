package com.example.tmy.tmy.controller;

import com.example.tmy.tmy.service.feign.HelloFeignService;
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
public class FeignController {

    @Autowired
    private HelloFeignService feignService;

    @GetMapping("feign/hi")
    public String feignHi(@RequestParam(defaultValue = "eureka") String name) {
        return feignService.hiService("feign/hi/" + name);
    }

}
