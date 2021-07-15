package com.example.tmy.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/7/15
 * @Desc
 */
@FeignClient("service-hi")
public interface HelloFeignService {

    @GetMapping("/hi")
    String hiService(@RequestParam(value = "name") String name);
}
