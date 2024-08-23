package com.example.javasecpasswordcracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @PostMapping("/result")
    public String hashSentence(@RequestParam("input") String input){
        //hashing logic
        //send to model
        //return the result page
        System.out.println("Sent to hash: " + input);
        return "result";
    }
}
