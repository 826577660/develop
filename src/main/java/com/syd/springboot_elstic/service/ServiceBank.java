package com.syd.springboot_elstic.service;

import com.syd.springboot_elstic.entity.Account;
import org.elasticsearch.rest.RestStatus;

import java.util.List;
import java.util.Map;

/**
 * Description: SpringBootDemo
 * Created by ubuntu on 2020/5/27 下午3:07
 */
public interface ServiceBank {
/*
    Account getDoc(String id);

    String saveOneDoc(Map<String,Object> map);

    RestStatus bulkDoc(List<Map<String,Object>> list);*/



    //=====================================================

    Account findDoc(String id);

    Iterable<Account> getAllByFirstname(String firstname);

    RestStatus bulkDoc(List<Map<String,Object>> list);

    RestStatus deleteDoc(String id);

    boolean exist(String id);
}
