package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){return "<h1>Hello from Spring!</h1>";}

    @RequestMapping(path = "/hello/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String helloToName(@PathVariable String name){
        return String.format("Nice to meet you, %s!", name);
    }

}
