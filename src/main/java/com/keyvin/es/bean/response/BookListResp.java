package com.keyvin.es.bean.response;

import com.keyvin.es.bean.entity.BookModel;
import lombok.Data;

import java.util.List;

/**
 * @author weiwh
 * @date 2020/7/12 11:55
 */
@Data
public class BookListResp {
    private int pageNo;
    private int pageSize;
    private long totalHits;
    private List<BookModel> list;

}
