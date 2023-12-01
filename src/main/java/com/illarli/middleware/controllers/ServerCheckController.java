package com.illarli.middleware.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerCheckController {

    @GetMapping("/check")
    public boolean checkServerStatus() {
        return true;
    }

    @GetMapping("/shut-down")
    public void turnoff() {
        System.exit(1);
    }
}
