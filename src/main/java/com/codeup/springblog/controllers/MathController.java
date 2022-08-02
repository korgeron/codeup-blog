package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MathController {

    public Integer addNums(Integer num1, Integer num2){
        return num1 + num2;
    }
    public Integer subtractNums(Integer num1, Integer num2){
        return num1 - num2;
    }
    public Integer multiplyNums(Integer num1, Integer num2){
        return num1 * num2;
    }
    public Integer divideNums(Integer num1, Integer num2){
        return num1 / num2;
    }

    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody()
    public String add(@PathVariable int num1, @PathVariable int num2){
        return String.format("Number %d added to number %d equals %d", num1, num2, addNums(num1, num2));
    }

    @GetMapping("subtract/{num1}/from/{num2}")
    @ResponseBody()
    public String subtract(@PathVariable int num1, @PathVariable int num2){
        return String.format("Number %d subtracted from number %d equals %d", num1, num2, subtractNums(num1, num2));
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody()
    public String multiply(@PathVariable int num1, @PathVariable int num2){
        return String.format("Number %d multiplied by %d equals %d", num1, num2, multiplyNums(num1, num2));
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody()
    public String divide(@PathVariable int num1, @PathVariable int num2){
        return String.format("Number %d divided by %d equals %d", num1, num2, divideNums(num1, num2));
    }

}
