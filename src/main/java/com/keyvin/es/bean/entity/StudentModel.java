package com.keyvin.es.bean.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author weiwh
 * @date 2020/7/19 11:34
 */
@Data
public class StudentModel {
    private Integer id;
    private Integer age;
    private String address;
    private String name;
    private String content;
    private String schoolName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date schoolTime;

}
