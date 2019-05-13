package com.example.Client6;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping("/")
    public String hello() {
        return "Hello";
    }

    @RequestMapping("/emp1")
    public String emp1() {
        return "emp1";
    }

    @RequestMapping("/emp2")
    public String emp2() {
        return "emp2";
    }


    /*@RequestMapping("/")
    public String home(Principal user) {
        return "Hello " + user.getName();
    }*/


}
