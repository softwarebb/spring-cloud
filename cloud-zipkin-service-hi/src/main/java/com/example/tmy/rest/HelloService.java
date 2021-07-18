package com.example.tmy.rest;

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

    public String hiService(String name) {
        return restTemplate.getForObject("http://localhost:8989/miya?name=" + name, String.class);
    }
}
