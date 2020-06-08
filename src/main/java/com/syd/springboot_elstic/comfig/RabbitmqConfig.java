package com.syd.springboot_elstic.comfig;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: SpringBoot_elstic
 * Created by ubuntu on 2020/6/4 下午3:05
 */
@Configuration
public class RabbitmqConfig {

    public static final String directexchange= "DIRECTEXCHANGE";
    public static final String fanout = "FANOUTEXCHANGE";
    public static final String topic = "TOPICEXCHANGE";


    @Bean
    public Queue queue(){
        return new Queue("test_queue");
    }
    @Bean
    public Queue queueTwo(){
        return new Queue("test_queue_two");
    }

    /*//Exchange类型为direct
    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.directExchange("directExchange").durable(true).build();
    }*/

    //Exchange类型为fanout
    @Bean
    public Exchange exchange(){
        return ExchangeBuilder.fanoutExchange(fanout).durable(true).build();
    }

    /*//绑定Exchange类型为direct,routingKey:wc
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(exchange()).with("wc").noargs();
    }*/

    @Bean
    public Binding bindingOne(){
        return BindingBuilder.bind(queue()).to(exchange()).with("fanout").noargs();
    }
    @Bean
    public Binding bindingTwo(){
        return BindingBuilder.bind(queueTwo()).to(exchange()).with("fanout").noargs();
    }

}
