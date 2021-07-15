package com.example.tmy.service.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/7/15
 * @Desc
 */
@Service
public class HelloService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiException")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/provider/hi?name=" + name, String.class);
    }
    public String hiException(String name) {
        return name + " hiException";
    }
}
