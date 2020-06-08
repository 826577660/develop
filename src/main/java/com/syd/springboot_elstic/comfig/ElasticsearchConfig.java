package com.syd.springboot_elstic.comfig;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;


import java.io.IOException;

/**
 * Description: SpringBootDemo
 * Created by ubuntu on 2020/5/27 下午2:19
 * 连接es以及关闭
 */
@Configuration
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

    /**
     * @author songyd
     * @date 2020/5/29 上午10:12
     * @param
     * @return org.elasticsearch.client.RestHighLevelClient
     */
    @Bean("client")
    @Override
    public RestHighLevelClient elasticsearchClient() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("10.0.5.167:9200")
                .build();
        return RestClients.create(clientConfiguration).rest();
    }

}
