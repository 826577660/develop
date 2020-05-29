package com.syd.springboot_elstic.controller;

import com.syd.springboot_elstic.entity.Account;
import com.syd.springboot_elstic.service.ServiceBank;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: SpringBootDemo
 * Created by ubuntu on 2020/5/27 下午3:21
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private ServiceBank serviceBank;

    /*@RequestMapping(value = "/getDoc",method = RequestMethod.GET)
    @ResponseBody
    public Object getDoc(String  id){
        return serviceBank.getDoc(id);
    }

    @RequestMapping(value = "/saveOneDoc",method = RequestMethod.PATCH)
    @ResponseBody
    public Object saveOneDoc(@RequestBody Map<String,Object> map){
        return serviceBank.saveOneDoc(map);
    }

    @RequestMapping(value = "/bulkDoc",method = RequestMethod.POST)
    @ResponseBody
    public Object bulkDoc(){
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("firstname","DongDong");
        map1.put("age",26);
        Map<String,Object> map2 = new HashMap<>();
        map1.put("firstname","SONGYD");
        map1.put("age",7);
        return serviceBank.bulkDoc(list);
    }*/

    //===================================================

    @RequestMapping(value = "/findDoc",method = RequestMethod.GET)
    @ResponseBody
    public Object findDoc(String  id){
        return serviceBank.findDoc(id);
    }

    @RequestMapping(value = "/findDocByName",method = RequestMethod.GET)
    @ResponseBody
    public Object findDocByName(String firstname){

        Iterable<Account> list = serviceBank.getAllByFirstname(firstname);
        return list;
    }

    @RequestMapping(value = "/bulkDoc",method = RequestMethod.POST)
    @ResponseBody
    public Object bulkDoc(){
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("firstname","songyadong");
        map1.put("age",26);
        Map<String,Object> map2 = new HashMap<>();
        map1.put("firstname","songyadong");
        map1.put("age",7);
        list.add(map1);
        list.add(map2);
        return serviceBank.bulkDoc(list);
    }

    @RequestMapping(value = "/deleteDoc",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteDoc(String id){

        RestStatus restStatus = serviceBank.deleteDoc(id);
        return restStatus;
    }

    @RequestMapping(value = "/exist",method = RequestMethod.POST)
    @ResponseBody
    public Object exist(String id){

        return serviceBank.exist(id);

    }

}
