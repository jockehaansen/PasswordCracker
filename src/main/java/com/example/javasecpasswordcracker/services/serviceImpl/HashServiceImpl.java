package com.example.javasecpasswordcracker.services.serviceImpl;

import com.example.javasecpasswordcracker.services.HashService;
import com.example.javasecpasswordcracker.utilities.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;

@Service
public class HashServiceImpl implements HashService {

    @Autowired
    ResourceLoader resourceLoader;
    private int sampleSize = 10000;

    public void hashInputs(Model model, String input){
        model.addAttribute("md5", HashUtil.md5(input));
        model.addAttribute("sha256", HashUtil.sha256(input));
    }

    @Override
    public void crackHashAndReturnPassword(Model model, String input) {
        try {
            File outputFile = Paths.get("data", "output.txt").toFile();
            System.out.println(outputFile.getAbsolutePath());

            try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
                String currentLine;
                boolean found = false;

                while ((currentLine = reader.readLine()) != null || sampleSize > 0) {
                    String[] parts = currentLine.split("\\|");
                    System.out.println(currentLine);

                    if (parts.length == 3) {
                        String password = parts[0].trim();
                        String md5Hash = parts[1].trim();
                        String sha256Hash = parts[2].trim();

                        if (input.trim().equals(md5Hash) || input.trim().equals(sha256Hash)) {
                            model.addAttribute("result", password);
                            found = true;
                            break;
                        }
                    }
                    sampleSize--;
                }
                if (!found) {
                    model.addAttribute("result", "No matching password found for the given hash.");
                }
            }

        } catch (Exception e) {
            model.addAttribute("result", "Error occurred while processing the file.");
            e.printStackTrace();
        }
    }
}
