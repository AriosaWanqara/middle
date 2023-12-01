package com.illarli.middleware.controllers;

import com.illarli.middleware.models.Printer;
import com.illarli.middleware.resolver.PrinterDTO;
import com.illarli.middleware.service.PrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/printers")
public class PrinterController {

    @Autowired
    private PrinterService printerService;

    @GetMapping("/list")
    public ResponseEntity<List<Printer>> index() {
        return new ResponseEntity<>(printerService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody PrinterDTO printerDTO) {
        if (printerService.save(printerDTO.createPrinter())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody PrinterDTO printerDTO) {
        if (printerService.save(printerDTO.createPrinter())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (printerService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
