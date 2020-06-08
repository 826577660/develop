package com.syd.springboot_elstic.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Description: SpringBoot_elstic
 * Created by ubuntu on 2020/6/3 下午5:14
 */
public class Producer {

    public static void main(String[] args) {/*
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        //设置rabbitmq地址
        factory.setHost("localhost");
        //建立到代理服务器到连接
        try {
            Connection conn = factory.newConnection();

            //获取通道
            Channel channel = conn.createChannel();
            //声明交换器
            String exchangeName = "hello-exchange";
            channel.exchangeDeclare(exchangeName,"direct",true);

            channel.queueDeclare("test_queue",true,false,false,null);

            String routingKey = "hello";
            //发布消息
            byte[] messageBodyByte = "这是我第一次发消息".getBytes();
            channel.basicPublish(exchangeName,routingKey,null,messageBodyByte);

            channel.close();
            conn.close();
        }catch (TimeoutException | IOException e){
            e.printStackTrace();
        }*/
    }
}

