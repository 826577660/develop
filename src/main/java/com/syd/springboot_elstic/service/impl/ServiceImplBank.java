package com.syd.springboot_elstic.service.impl;

import com.syd.springboot_elstic.dao.DaoBank;
import com.syd.springboot_elstic.entity.Account;
import com.syd.springboot_elstic.service.ServiceBank;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Description: SpringBootDemo
 * Created by ubuntu on 2020/5/27 下午3:08
 */
@Service
public class ServiceImplBank implements ServiceBank {

    /*private static final String INDEX_NAME = "bank";

    private RestHighLevelClient client = ElasticsearchConfig.restHighLevelClient();

    @Override
    public Account getDoc(String id) {
        ObjectMapper mapper = new ObjectMapper();
        GetRequest getRequest = new GetRequest(INDEX_NAME,String.valueOf(id));
        try{
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            if (getResponse.isExists()){
                String source = getResponse.getSourceAsString();

                Account module = mapper.readValue(source,Account.class);

                ElasticsearchConfig.close(client);
                return module;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        ElasticsearchConfig.close(client);
        return null;
    }

    @Override
    public String saveOneDoc(Map<String, Object> map) {

        RestHighLevelClient client = ElasticsearchConfig.restHighLevelClient();

        IndexRequest request = new IndexRequest("bank").source(map);
        IndexResponse response = null;
        try {
            response = client.index(request,RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }
        ElasticsearchConfig.close(client);
        String id = response.getId();
        return response.getIndex()+"/"+id.toString();
    }

    @Override
    public RestStatus bulkDoc(List<Map<String, Object>> list) {
        RestHighLevelClient client = ElasticsearchConfig.restHighLevelClient();

        BulkRequest request = new BulkRequest();
        for (Map<String,Object> l : list) {
            request.add(new IndexRequest("bank").source(l));
        }
        BulkResponse responses = null;
        try {
            responses = client.bulk(request,RequestOptions.DEFAULT);
        }catch (IOException e){
            e.printStackTrace();
        }
        return responses.status();
    }*/

    //==========================================================================

    @Autowired
    private DaoBank daoBank;

    @Autowired
    @Qualifier("client")
    private RestHighLevelClient client;

    @Override
    public Account findDoc(String id) {
        return daoBank.findById(id).get();
    }

    @Override
    public Iterable<Account> getAllByFirstname(String firstname) {
        Iterable<Account> list = daoBank.findAllByFirstname(firstname);
        return list;
    }

    @Override
    public RestStatus bulkDoc(List<Map<String, Object>> list) {
        BulkRequest request = new BulkRequest();
        for (Map<String,Object> l : list) {
            request.add(new IndexRequest("bank").source(l));
        }
        BulkResponse responses = null;
        try {
            responses = client.bulk(request,RequestOptions.DEFAULT);
            client.close();
            return responses.status();
        }catch (IOException e){
            e.printStackTrace();
        }
        return responses.status();
    }

    @Override
    public RestStatus deleteDoc(String id) {

        DeleteRequest request = new DeleteRequest("bank",id);
        DeleteResponse response = null;
        try {
            response = client.delete(request,RequestOptions.DEFAULT);
            client.close();
            return response.status();
        }catch (IOException e){
            e.printStackTrace();
        }
        return response.status();
    }

    @Override
    public boolean exist(String id) {
        GetRequest request = new GetRequest("bank",id);
        boolean b = false;
        try {
            b = client.exists(request,RequestOptions.DEFAULT);
            client.close();
            return b;
        }catch (IOException e){
            e.printStackTrace();
        }
        return b;

    }
}
