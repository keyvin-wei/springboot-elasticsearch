package com.keyvin.es.service;

import com.keyvin.es.bean.entity.StudentModel;

import java.io.IOException;

/**
 * @author weiwh
 * @date 2020/7/12 10:32
 */
public interface ElasticsearchService {

    boolean createIndex(String indexName) throws IOException;

    boolean createIndexByMapping(String indexName) throws IOException;

    boolean deleteIndex(String indexName) throws IOException;

    boolean existsIndex(String indexName) throws IOException;

    void saveStudent(StudentModel model);
}
