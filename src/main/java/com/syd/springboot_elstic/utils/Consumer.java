package com.syd.springboot_elstic.utils;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Description: SpringBoot_elstic
 * Created by ubuntu on 2020/6/3 下午5:27
 */
public class Consumer{
    public static void main(String[] args) throws IOException, TimeoutException {/*
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("localhost");

        Connection conn = factory.newConnection();

        final Channel channel = conn.createChannel();

        String exchangeName = "hello-exchange";
        channel.exchangeDeclare(exchangeName,"direct",true);

//        String queueName = channel.queueDeclare().getQueue();
//        System.out.println(queueName);
        String routingKey = "hello";

        channel.queueBind("test_queue",exchangeName,routingKey);

        while (true){
            //消费消息
            boolean autoAck = false;

            String consumerTag = "";
            channel.basicConsume("test_queue",autoAck,consumerTag,new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String routingKey = envelope.getRoutingKey();
                    String contentType = properties.getContentType();
                    System.out.println("消费的路由键：" + routingKey);
                    System.out.println("消费的内容类型：" + contentType);
                    long deliveryTag = envelope.getDeliveryTag();
                    //确认消息
                    channel.basicAck(deliveryTag,false);
                    System.out.println("消费的消息体内容：");
                    String bodyStr = new String(body, "UTF-8");
                    System.out.println(bodyStr);
                }
            });
        }*/

    }
}
