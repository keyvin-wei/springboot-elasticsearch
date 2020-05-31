package com.keyvin.es.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiwh
 * @date 2019/10/21 19:14
 */
@RestController
public class IndexController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/index")
    public String index(){
        return "welcome to front,端口号：" + port;
    }

    @RequestMapping("/up")
    public String up(){
        return "UP";
    }

}
