package com.egoo.seckill.bigfish.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "home";
    }
    @RequestMapping("/{page}")
    public String page(@PathVariable String page){
        return page;
    }
}
