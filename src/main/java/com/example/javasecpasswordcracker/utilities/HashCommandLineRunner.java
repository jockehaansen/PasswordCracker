package com.example.javasecpasswordcracker.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Profile("HashRunner")
public class HashCommandLineRunner implements CommandLineRunner {

    @Autowired
    ResourceLoader resourceLoader;

    private final Logger logger = Logger.getLogger(HashCommandLineRunner.class.getName());
    private int sampleSize = 10000; //safety exit satt av sample size på lösenorden

    @Override
    public void run(String... args) throws IOException {
        try {
            File inputFile = resourceLoader.getResource("classpath:data/passwords.txt").getFile();
            File outputFile = resourceLoader.getResource("classpath:data/output.txt").getFile();
            readAndWriteHash(inputFile, outputFile);
        } catch (Exception e){
            logger.warning("Error managing your files, hashing not possible");
            e.printStackTrace();
        }
    }

    public void readAndWriteHash(File inputFile, File outputFile){
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {

            String currentLine;
            while ((currentLine = reader.readLine()) != null || sampleSize < 0) {
                String appendHash = "|" + HashUtil.md5(currentLine) + "|" + HashUtil.sha256(currentLine);
                currentLine += appendHash;
                writer.write(currentLine + "\n");
                sampleSize--; //safety exit satt av sample size på lösenorden
            }
            logger.log(Level.INFO, "Passwords have been hashed successfully");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "An IOException has occurred: ", e);
        }
    }
}