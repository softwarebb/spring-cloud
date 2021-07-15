package com.example.tmy.tmy.service.feign.impl;

import com.example.tmy.tmy.service.feign.HelloFeignService;
import org.springframework.stereotype.Component;

@Component
public class HelloServiceHiHystric implements HelloFeignService {

    @Override
    public String hiService(String name) {
        return name + "hiException";
    }
}
