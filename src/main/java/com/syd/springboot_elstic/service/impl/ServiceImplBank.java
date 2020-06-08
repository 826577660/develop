package com.syd.springboot_elstic.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.syd.springboot_elstic.dao.DaoBank;
import com.syd.springboot_elstic.entity.Account;
import com.syd.springboot_elstic.service.ServiceBank;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Cancellable;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = new HashMap<String,Object>();
        GetRequest request = new GetRequest();
        request.index("bank").id("TlFgWXIBCY3yqKirllPf");
        GetResponse response = null;
        Account account = null;
        try{
            response = client.get(request,RequestOptions.DEFAULT);
            if (response.isExists()){
                map = response.getSourceAsMap();
                account = mapper.readValue(mapper.writeValueAsString(map),Account.class);
                return account;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return account;
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




    @Override
    public List<Map<String,Object>> getDoc(int fieldName) {
        SearchRequest searchRequest = new SearchRequest("bank");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        QueryBuilder query =  QueryBuilders.matchQuery("age",fieldName);
        builder.query(query);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field field = new HighlightBuilder.Field("age");
        field.highlighterType("unified");
        highlightBuilder.field(field);
        builder.highlighter(highlightBuilder);
        builder.from(0);
        builder.size(10);
        searchRequest.source(builder);
        final SearchResponse response = null;
        List<Map<String,Object>> searchList = new ArrayList<Map<String,Object>>();

        //异步执行
        ActionListener<SearchResponse> listener = new ActionListener<SearchResponse>() {
            @Override
            public void onResponse(SearchResponse searchResponse) {
                System.out.println("执行完成:"+searchResponse);
                //todo
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit:searchHit){
                    Map<String,Object> map = hit.getSourceAsMap();
                    searchList.add(map);
                }

                System.out.println("result:"+searchList);
            }

            @Override
            public void onFailure(Exception e) {
                e.printStackTrace();
            }
        };
        client.searchAsync(searchRequest,RequestOptions.DEFAULT,listener);
        return searchList;
    }
}
