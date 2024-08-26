package com.example.javasecpasswordcracker.services;

import com.example.javasecpasswordcracker.utilities.HashUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class HashService {
    public Model hashInputs(Model model, String input){
        model.addAttribute("md5", HashUtil.md5(input));
        model.addAttribute("sha256", HashUtil.sha256(input));
        return model;
    }
}
