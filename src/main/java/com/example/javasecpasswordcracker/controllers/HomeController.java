package com.example.javasecpasswordcracker.controllers;

import com.example.javasecpasswordcracker.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private HashService hashService;

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @PostMapping("/result")
    public String hashSentence(@RequestParam("input") String input, Model model){
        hashService.hashInputs(model, input);
        System.out.println("Sent to hash: " + input);
        return "result";
    }
}
