package com.keyvin.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.keyvin.es.bean.entity.StudentModel;
import com.keyvin.es.bean.vo.BookAddVo;
import com.keyvin.es.service.ElasticsearchService;
import com.keyvin.es.utils.Utils;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weiwh
 * @date 2020/7/12 10:32
 */
@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {
    private Logger log = LoggerFactory.getLogger(ElasticsearchServiceImpl.class);
    @Autowired
    private RestHighLevelClient client;

    @Override
    public boolean createIndex(String indexName) throws IOException {
        CreateIndexRequest index = new CreateIndexRequest(indexName);
        index.settings();
        CreateIndexResponse response = client.indices().create(index, RequestOptions.DEFAULT);
        if(response.isAcknowledged()){
            log.info("创建索引{}成功", indexName);
        }else{
            log.info("创建索引{}失败", indexName);
        }
        return response.isAcknowledged();
    }

    @Override
    public boolean createIndexByMapping(String indexName) throws IOException {
        IndexRequest index = new IndexRequest(indexName);
        XContentBuilder builder = XContentFactory.jsonBuilder()
                        .startObject("id").field("type", "integer").endObject()
                        .startObject("age").field("type", "integer").endObject()
                        .startObject("address").field("type", "text").endObject()
                        .startObject("name").field("type", "text").field("analyzer", "ik_max_word").endObject()
                        .startObject("content").field("type", "text").field("analyzer", "ik_smart").endObject()
                        .startObject("school_name").field("type", "text").field("analyzer", "ik_smart").endObject()
                        .startObject("school_time").field("type", "date").endObject();
        index.type("st");
        index.source(builder);
        IndexResponse response = client.index(index, RequestOptions.DEFAULT);
            log.info("创建索引{}成功", indexName);
            log.info(JSON.toJSONString(response));

        return true;
    }

    @Override
    public boolean deleteIndex(String indexName) throws IOException{
        DeleteIndexRequest index = new DeleteIndexRequest(indexName);
        AcknowledgedResponse response = client.indices().delete(index, RequestOptions.DEFAULT);
        if(response.isAcknowledged()){
            log.info("删除索引{}成功", indexName);
        }else{
            log.info("删除索引{}失败", indexName);
        }
        return response.isAcknowledged();
    }

    @Override
    public boolean existsIndex(String indexName) throws IOException{
        GetIndexRequest index = new GetIndexRequest();
        index.indices(indexName);
        boolean flag = client.indices().exists(index, RequestOptions.DEFAULT);
        return flag;
    }

    @Override
    public void saveStudent(StudentModel model) {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("id", model.getId());
        jsonMap.put("age", model.getAge());
        jsonMap.put("address", model.getAddress());
        jsonMap.put("name", model.getName());
        jsonMap.put("content", model.getContent());
        jsonMap.put("schoolName", model.getSchoolName());
        jsonMap.put("schoolTime", model.getSchoolTime());

        IndexRequest indexRequest = new IndexRequest("student", "st");
        indexRequest.source(jsonMap);

        client.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse response) {
                String index = response.getIndex();
                String id = response.getId();
                long version = response.getVersion();
                log.info("Index: {}, Id: {}, Version: {}", index, id, version);

                if (response.getResult() == DocWriteResponse.Result.CREATED) {
                    log.info("插入成功");
                } else if (response.getResult() == DocWriteResponse.Result.UPDATED) {
                    log.info("更新成功");
                }
                ReplicationResponse.ShardInfo shardInfo = response.getShardInfo();
                if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
                    log.warn("部分分片写入成功");
                }
                if (shardInfo.getFailed() > 0) {
                    for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                        String reason = failure.reason();
                        log.warn("失败原因: {}", reason);
                    }
                }
            }

            @Override
            public void onFailure(Exception e) {
                log.error(e.getMessage(), e);
            }
        });
    }
}
