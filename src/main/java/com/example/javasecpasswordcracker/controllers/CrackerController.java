package com.example.javasecpasswordcracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CrackerController {

    @GetMapping("/crackstation")
    public String crackstationPage(){
        return "crackstation";
    }

    @PostMapping("/crackstation/crackpassword")
    public String crackPassword(@RequestParam("input") String input, Model model){
        //do things
        model.addAttribute("result", input);
        return "crackstation";
    }

}
