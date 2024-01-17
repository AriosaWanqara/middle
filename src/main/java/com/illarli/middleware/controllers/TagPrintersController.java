package com.illarli.middleware.controllers;

import com.illarli.middleware.models.TagPrinter;
import com.illarli.middleware.resolver.TagPrinterDTO;
import com.illarli.middleware.service.TagPrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody TagPrinterDTO tagPrinterDTO) {
        if (!tagPrinterService.save(tagPrinterDTO.createTagPrinter())) {
            return new ResponseEntity<>("can't create", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
