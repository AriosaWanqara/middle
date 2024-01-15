package com.illarli.middleware.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/server")
public class ServerCheckController {

    private final Logger logger = LoggerFactory.getLogger(ServerCheckController.class);

    @GetMapping("/check")
    public boolean checkServerStatus() {
        return true;
    }

    @GetMapping("/shut-down")
    public void turnoff() {
        System.exit(1);
    }

    @GetMapping("/logs")
    public String getLogs() {
        try {
            Path logFile = Paths.get("mylog.log");
            return new String(Files.readAllBytes(logFile));
        } catch (Exception e) {
            logger.warn("Error getting logs");
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
