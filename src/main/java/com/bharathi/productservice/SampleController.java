package com.bharathi.productservice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SampleController {

    @GetMapping("/hello")
    public String sayHelloWorld() {
        return "Hello World";
    }

    @GetMapping("/{name}/{number}")
    public String sayHello(@PathVariable String name, @PathVariable int number) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= number; i++) {
            sb.append("Hello ").append(name).append(" ");
        }

        return sb.toString();
    }


}
