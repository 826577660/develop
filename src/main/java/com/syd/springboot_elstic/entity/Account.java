package com.syd.springboot_elstic.entity;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Description: SpringBootDemo
 * Created by ubuntu on 2020/5/27 下午3:00
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "bank")
public class Account {

    private String id;
    private Long account_number;
    private String address;
    private Long age;
    private Long balance;
    private String city;
    private String email;
    private String employer;
    private String firstname;
    private String gender;
    private String lastname;
    private String state;

}
