package com.syd.springboot_elstic.dao;

import com.syd.springboot_elstic.entity.Account;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Description: SpringBootDemo
 * Created by ubuntu on 2020/5/28 下午2:14
 */
public interface DaoBank extends ElasticsearchRepository<Account,String> {

    Iterable<Account> findAllByFirstname(String firstname);

}
