package com.example.javasecpasswordcracker.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface HashService {
    void hashInputs(Model model, String input);

    void crackHashAndReturnPassword(Model model, String input);
}
