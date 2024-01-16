package com.illarli.middleware.controllers;

import com.illarli.middleware.infrastructure.print.ZebraZPL;
import com.illarli.middleware.models.TagPrinter;
import com.illarli.middleware.service.TagPrinterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/send-tag-printer")
public class SendTagPrinter {

    @Autowired
    private TagPrinterService tagPrinterService;

    @PostMapping("")
    public ResponseEntity<?> index(@RequestBody String zpl) {
        List<TagPrinter> tagPrinters = tagPrinterService.getAll();
        if (tagPrinters.isEmpty()) {
            return new ResponseEntity<>("Printer not found", HttpStatus.CONFLICT);
        }
        ZebraZPL zebraZPL = new ZebraZPL(tagPrinters.get(0));
        if (!zebraZPL.print(zpl)) {
            return new ResponseEntity<>("Can't print", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/print-list")
    public ResponseEntity<?> printList(@RequestBody String[] zpls) {
        List<TagPrinter> tagPrinters = tagPrinterService.getAll();
        if (tagPrinters.isEmpty()) {
            return new ResponseEntity<>("Printer not found", HttpStatus.CONFLICT);
        }
        ZebraZPL zebraZPL = new ZebraZPL(tagPrinters.get(0));
        if (!zebraZPL.print(zpls)) {
            return new ResponseEntity<>("Can't print", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
