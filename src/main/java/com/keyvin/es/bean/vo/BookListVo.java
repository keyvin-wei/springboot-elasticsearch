package com.keyvin.es.bean.vo;

import lombok.Data;

/**
 * @author weiwh
 * @date 2020/7/12 9:49
 */
@Data
public class BookListVo {
    private int pageNo;
    private int pageSize;
    private String name;
    private String author;
    private String status;
    private String sellTime;
    private String categories;

}
