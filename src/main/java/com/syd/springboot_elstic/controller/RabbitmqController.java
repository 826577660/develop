package com.syd.springboot_elstic.controller;

import com.syd.springboot_elstic.comfig.RabbitmqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description: SpringBoot_elstic
 * Created by ubuntu on 2020/6/4 下午3:23
 */
@Controller
@RequestMapping("/mq")
public class RabbitmqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "direct",method = RequestMethod.GET)
    @ResponseBody
    public String  DirectPublisher(){
        rabbitTemplate.convertAndSend("directExchange","wc","direct发消息");
        return "已发送";
    }

    @RequestMapping(value = "fanout",method = RequestMethod.GET)
    @ResponseBody
    public String  FanoutPublisher(){
        rabbitTemplate.convertAndSend(RabbitmqConfig.fanout,null,"fanout发消息");
        return "已发送";
    }
}
