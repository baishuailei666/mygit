package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
@RestController
public class DemoController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/get")
    public Map getInfo() {
        Map mapInfo = new HashMap(1);
        mapInfo.put("name","launcher");
        mapInfo.put("age",22);
        return mapInfo;
    }
}
