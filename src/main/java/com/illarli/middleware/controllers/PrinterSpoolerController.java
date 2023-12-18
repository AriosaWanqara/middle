package com.illarli.middleware.controllers;

import com.illarli.middleware.infrastructure.print.EscPosCoffee;
import com.illarli.middleware.models.Printer;
import com.illarli.middleware.models.PrinterSpooler;
import com.illarli.middleware.service.PrinterService;
import com.illarli.middleware.service.PrinterSpoolerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/printer-spooler")
public class PrinterSpoolerController {

    @Autowired
    private PrinterSpoolerService printerSpoolerService;
    @Autowired
    private PrinterService printerService;

    @GetMapping("")
    public ResponseEntity<List<PrinterSpooler>> index() {
        return new ResponseEntity<>(this.printerSpoolerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/print")
    public void rePrint() {
        List<Printer> printers = this.printerService.getAll();
        List<PrinterSpooler> printSpooler = this.printerSpoolerService.getAll();
        List<String> ids = new ArrayList<>(List.of());
        EscPosCoffee escPosCoffee;
        for (PrinterSpooler printerSpooler : printSpooler) {
            for (Printer printer : printers) {
                if (printer.deserializeDocumentType().contains(printerSpooler.getDocumentTypeId())) {
                    ids.add(printerSpooler.getId());
                    escPosCoffee = new EscPosCoffee(printer);
                    printerSpooler.print(escPosCoffee);
                }
            }
        }
        if (!ids.isEmpty()) printerSpoolerService.removeAllByIds(ids);
    }

//    @DeleteMapping("/delete/{id}")
//    public void delete(@PathVariable String id) {
//        this.printerSpoolerService.removeById(id);
//    }
}
