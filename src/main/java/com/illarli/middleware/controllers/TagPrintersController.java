package com.illarli.middleware.controllers;

import com.illarli.middleware.models.TagPrinter;
import com.illarli.middleware.service.TagPrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tag-printer")
public class TagPrintersController {
    @Autowired
    private TagPrinterService tagPrinterService;

    @GetMapping("")
    public ResponseEntity<List<TagPrinter>> index() {
        return new ResponseEntity<>(tagPrinterService.getAll(), HttpStatus.OK);
    }
}
