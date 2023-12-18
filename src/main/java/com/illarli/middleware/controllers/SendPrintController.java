package com.illarli.middleware.controllers;

import com.illarli.middleware.infrastructure.print.EscPosCoffee;
import com.illarli.middleware.models.Printer;
import com.illarli.middleware.models.PrinterSpooler;
import com.illarli.middleware.resolver.*;
import com.illarli.middleware.service.PrinterService;
import com.illarli.middleware.service.PrinterSpoolerService;
import com.illarli.middleware.service.SendPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/send-print")
public class SendPrintController {

    @Autowired
    private PrinterService printerService;
    @Autowired
    private PrinterSpoolerService printerSpoolerService;

    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestBody Printer printer) {
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer);
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.printTest()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/list")
    public ResponseEntity<String[]> SOPrinters() {
        EscPosCoffee escPosCoffee = new EscPosCoffee();
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        return new ResponseEntity<>(sendPrintService.getSOPrinters(), HttpStatus.OK);
    }

    @PostMapping("/print-text/{id}")
    public ResponseEntity<?> printText(@Valid @RequestBody PrintTextDTO text, @PathVariable String id) {
        Optional<Printer> printer = this.printerService.getPrinterById(id);
        if (printer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.printText(text)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-media/{id}")
    public ResponseEntity<?> printMedia(@Valid @RequestBody PrintMediaDTO media, @PathVariable String id) {
        Optional<Printer> printer = this.printerService.getPrinterById(id);
        if (printer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.printMedia(media)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-title/{id}")
    public ResponseEntity<?> printTitle(@Valid @RequestBody PrintTitleDTO title, @PathVariable String id) {
        Optional<Printer> printer = this.printerService.getPrinterById(id);
        if (printer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.printTitle(title)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/cut/{id}")
    public ResponseEntity<?> cut(@PathVariable String id) {
        Optional<Printer> printer = this.printerService.getPrinterById(id);
        if (printer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.cut()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-quotation/{code}")
    public ResponseEntity<?> printQuotation(@Valid @RequestBody PrintQuotationDTO quotation, @PathVariable String code) {
        Optional<Printer> printer = this.printerService.getPrinterByDocumentTypeCode(code);
        if (printer.isEmpty()) {
            PrinterSpooler printerSpooler = quotation.createPrinterSpooler();
            printerSpoolerService.save(printerSpooler);
            return new ResponseEntity<>("Printer not found", HttpStatus.CONFLICT);
        }
        System.out.println("Force Accounting: " + quotation.isForceAccounting());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.quotation(quotation)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-cash-drawer/{code}")
    public ResponseEntity<?> printCashDrawerDetail(@Valid @RequestBody PrintCashDrawerCloseDetailDTO cashDrawerDetail, @PathVariable String code) {
        Optional<Printer> printer = this.printerService.getPrinterByDocumentTypeCode(code);
        if (printer.isEmpty()) {
            return new ResponseEntity<>("Printer not found", HttpStatus.CONFLICT);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.cashDrawerDetail(cashDrawerDetail)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-command/{code}")
    public ResponseEntity<?> printCommand(@Valid @RequestBody PrintCommandDTO command, @PathVariable String code) {
        Optional<Printer> printer = this.printerService.getPrinterByDocumentTypeCode(code);
        if (printer.isEmpty()) {
            PrinterSpooler printerSpooler = command.createPrinterSpooler();
            printerSpoolerService.save(printerSpooler);
            return new ResponseEntity<>("Printer not found", HttpStatus.CONFLICT);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.command(command)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-electronic/{code}")
    public ResponseEntity<?> printElectronicInvoice(@Valid @RequestBody PrintElectronicInvoiceDTO electronicInvoice, @PathVariable String code, HttpServletResponse response) {
        Optional<Printer> printer = this.printerService.getPrinterByDocumentTypeCode(code);
        if (printer.isEmpty()) {
            PrinterSpooler printerSpooler = electronicInvoice.createPrinterSpooler();
            printerSpoolerService.save(printerSpooler);
            return new ResponseEntity<>("Printer not found", HttpStatus.CONFLICT);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.electronicInvoice(electronicInvoice)) {
            return new ResponseEntity<>("OKIII", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-pre-ticket/{code}")
    public ResponseEntity<?> printPreTicket(@Valid @RequestBody PrintPreTicketDTO preTicket, @PathVariable String code) {
        Optional<Printer> printer = this.printerService.getPrinterByDocumentTypeCode(code);
        if (printer.isEmpty()) {
            PrinterSpooler printerSpooler = preTicket.createPrinterSpooler();
            printerSpoolerService.save(printerSpooler);
            return new ResponseEntity<>("Printer not found", HttpStatus.CONFLICT);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.preTicket(preTicket)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-voucher/{code}")
    public ResponseEntity<?> printVoucher(@Valid @RequestBody PrintVoucherDTO voucher, @PathVariable String code) {
        Optional<Printer> printer = this.printerService.getPrinterByDocumentTypeCode(code);
        if (printer.isEmpty()) {
            PrinterSpooler printerSpooler = voucher.createPrinterSpooler();
            printerSpoolerService.save(printerSpooler);
            return new ResponseEntity<>("Printer not found", HttpStatus.CONFLICT);
        }
        System.out.println(voucher.toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.voucher(voucher)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
