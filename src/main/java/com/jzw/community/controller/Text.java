package com.jzw.community.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Text {

    @RequestMapping("/hello")
    public String text(){
        return "hello";
    }
}
