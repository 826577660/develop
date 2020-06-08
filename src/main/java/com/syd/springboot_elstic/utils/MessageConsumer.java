package com.syd.springboot_elstic.utils;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: SpringBoot_elstic
 * Created by ubuntu on 2020/6/4 下午3:20
 */
@Component
public class MessageConsumer {

    //消费消息
    @RabbitListener(queues = "test_queue")
    public void consum(Message message, Channel channel) throws IOException {
        MessageProperties messageProperties = message.getMessageProperties();
        long tag = messageProperties.getDeliveryTag();
        //手动消息确认
        channel.basicAck(tag,false);
        String str = new String(message.getBody());
        System.out.println("接受消费信息："+str);
        Map<String,Object> map = new HashMap<>();
        map.put("","");
    }

    @RabbitListener(queues = "test_queue_two")
    public void consumTwo(String msg){
        System.out.println("接受消费信息："+msg);
    }
}
