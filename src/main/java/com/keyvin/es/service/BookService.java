package com.keyvin.es.service;

import com.keyvin.es.bean.entity.BookModel;
import com.keyvin.es.bean.vo.BookRequestVO;

import java.util.Map;
/**
 * @author weiwh
 * @date 2020/7/12 10:12
 */
public interface BookService {
    /**
     * 列表查询
     */
    Map<String, Object> list(BookRequestVO bookRequestVO);
    /**
     * 保存新增
     */
    void save(BookModel bookModel);
    /**
     * 编辑修改
     */
    void update(BookModel bookModel);
    /**
     * 删除单个
     */
    void delete(int id);
    /**
     * 查看详情
     */
    BookModel detail(int id);
}