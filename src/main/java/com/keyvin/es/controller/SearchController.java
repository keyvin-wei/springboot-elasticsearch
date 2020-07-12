package com.keyvin.es.controller;

import com.keyvin.es.service.ElasticsearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiwh
 * @date 2020/7/12 10:13
 */
@Api(tags = "Es接口")
@RestController
@RequestMapping("/book")
public class SearchController {
    @Autowired
    private ElasticsearchService elasticsearchService;

    @ApiOperation(value = "测试")
    @ApiResponses(@ApiResponse(code=200, message = "OK"))
    @GetMapping("/up")
    public String up(){
        return "UP";
    }

}
