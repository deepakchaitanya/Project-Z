package com.example.projectz.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @RequestMapping
    public String ping() {
            return "Hello World";
    }
}
