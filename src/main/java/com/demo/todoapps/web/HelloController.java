package com.demo.todoapps.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String helloController(@RequestParam(name="name",required = false,defaultValue = "world") String name, Model model){

        model.addAttribute("name",name);
        return "hello";
    }
}
