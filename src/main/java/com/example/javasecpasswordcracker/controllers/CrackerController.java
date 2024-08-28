package com.example.javasecpasswordcracker.controllers;

import com.example.javasecpasswordcracker.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CrackerController {

    @Autowired
    private HashService hashService;

    @GetMapping("/crackstation")
    public String crackstationPage(){
        return "crackstation";
    }

    @PostMapping("/crackstation/crackpassword")
    public String crackPassword(@RequestParam("input") String input, Model model){
        hashService.crackHashAndReturnPassword(model, input);
        return "crackstation";
    }

}
