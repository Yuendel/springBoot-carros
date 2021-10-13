package com.example.carros.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class indexController {

    @GetMapping
    public String get() {
    return "Get Spring Boot";
    }

    @PostMapping
    public String post() {
        return "Post Spring Boot";
    }

    @DeleteMapping
    public String delete() {
        return "Delete Spring Boot";
    }

    @PutMapping
    public String put() {
        return "Put Spring Boot";
    }
}