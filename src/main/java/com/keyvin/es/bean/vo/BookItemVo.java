package com.keyvin.es.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author weiwh
 * @date 2020/7/12 11:30
 */
@Data
public class BookItemVo {

    @NotBlank(message = "图书ID不能为空")
    @ApiModelProperty(value = "图书ID", example = "162ca564eb63430ea875ba0358699ae9", required = true)
    private String id;

}
