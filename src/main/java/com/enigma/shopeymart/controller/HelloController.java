package com.enigma.shopeymart.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello() {
        return "<h1> Hello World </h1>";
    }
    @GetMapping(value = "/hello/v1")
    public String[] getHobies(){
        return new String[]{"Makan","Game","Tidur","Jangan Lupa Ibadah"};
    }
    @GetMapping(value = "/search{key}")
    public String getRequestParam(@RequestParam String key){
        return "<h1>" +key+"</h1>";
    }
    @GetMapping("/data/{id}/{title}")
    public String getDataById(@PathVariable String id, @PathVariable String title) {
        return "Data" + id + title;
    }
}
