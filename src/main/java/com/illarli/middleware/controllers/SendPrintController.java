package com.illarli.middleware.controllers;

import com.illarli.middleware.infrastructure.print.EscPosCoffee;
import com.illarli.middleware.models.Printer;
import com.illarli.middleware.resolver.*;
import com.illarli.middleware.service.PrinterService;
import com.illarli.middleware.service.SendPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/send-print")
public class SendPrintController {

    @Autowired
    private PrinterService printerService;

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

    @PostMapping("/open-drawer/{id}")
    public ResponseEntity<?> openCashDrawer(@PathVariable String id) {
        Optional<Printer> printer = this.printerService.getPrinterById(id);
        if (printer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.openCashDrawer()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-quotation/{id}")
    public ResponseEntity<?> printQuotation(@Valid @RequestBody PrintQuotationDTO quotation, @PathVariable String id) {
        Optional<Printer> printer = this.printerService.getPrinterById(id);
        if (printer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("Force Accounting: " + quotation.isForceAccounting());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.quotation(quotation)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-cash-drawer/{id}")
    public ResponseEntity<?> printCashDrawerDetail(@Valid @RequestBody PrintCashDrawerCloseDetailDTO cashDrawerDetail, @PathVariable String id) {
        Optional<Printer> printer = this.printerService.getPrinterById(id);
        if (printer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.cashDrawerDetail(cashDrawerDetail)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-command/{id}")
    public ResponseEntity<?> printCommand(@Valid @RequestBody PrintCommandDTO command, @PathVariable String id) {
        Optional<Printer> printer = this.printerService.getPrinterById(id);
        if (printer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.command(command)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-electronic/{id}")
    public ResponseEntity<?> printElectronicInvoice(@Valid @RequestBody PrintElectronicInvoiceDTO electronicInvoice, @PathVariable String id) {
        Optional<Printer> printer = this.printerService.getPrinterById(id);
        if (printer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.electronicInvoice(electronicInvoice)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-pre-ticket/{id}")
    public ResponseEntity<?> printPreTicket(@Valid @RequestBody PrintPreTicketDTO preTicket, @PathVariable String id) {
        Optional<Printer> printer = this.printerService.getPrinterById(id);
        if (printer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.preTicket(preTicket)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/print-voucher/{id}")
    public ResponseEntity<?> printVoucher(@Valid @RequestBody PrintVoucherDTO voucher, @PathVariable String id) {
        Optional<Printer> printer = this.printerService.getPrinterById(id);
        if (printer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(printer.get().toString());
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.voucher(voucher)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
