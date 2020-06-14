package com.keyvin.es.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiwh
 * @date 2019/10/21 19:14
 */
@Api(tags = "index测试")
@RestController
public class IndexController {
    @Value("${server.port}")
    private String port;

    @ApiOperation(value = "index接口")
    @ApiResponses(@ApiResponse(code = 200, message = "成功了"))
    @GetMapping("/index")
    public String index(){
        return "welcome to front,端口号：" + port;
    }

    @ApiOperation(value = "up接口")
    @ApiResponses(@ApiResponse(code = 200, message = "成功了"))
    @GetMapping("/up")
    public String up(){
        return "UP";
    }

}
