package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class EmployeeController {
    @GetMapping("/hello/{name}")
    public String Hello(@PathVariable  String name ) {
        return "Hello you passed "+name;
    }
}
