package com.example.tmy.controller;

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
public class MiyaController {


    @GetMapping("/miya")
    public String restHi(@RequestParam(defaultValue = "eureka") String name) {

        return "miya" + name;
    }
}
