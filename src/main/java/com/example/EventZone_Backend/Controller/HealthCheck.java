package com.example.EventZone_Backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/health-check")
public class HealthCheck {

    @GetMapping
    public String healthCheckController(){
        return "Hello World!";
    }

}
