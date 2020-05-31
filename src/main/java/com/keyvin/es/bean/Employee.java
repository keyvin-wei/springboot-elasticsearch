package com.keyvin.es.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author weiwh
 * @date 2020/3/29 23:58
 */
@Data
@ToString
@NoArgsConstructor
public class Employee {

    private String id;
    private Long version;
    String firstName;
    String lastName;
    String age;
    String[] interests;

}
