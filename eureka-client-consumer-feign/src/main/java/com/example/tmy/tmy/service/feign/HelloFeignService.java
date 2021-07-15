package com.example.tmy.tmy.service.feign;

import com.example.tmy.tmy.service.feign.impl.HelloServiceHiHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2021/7/15
 * @Desc
 */
@FeignClient(value = "service-hi",fallback = HelloServiceHiHystric.class)
public interface HelloFeignService {

    @GetMapping("/provider/hi")
    String hiService(@RequestParam(value = "name") String name);
}
