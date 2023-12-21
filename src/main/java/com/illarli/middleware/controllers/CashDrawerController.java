package com.illarli.middleware.controllers;

import com.illarli.middleware.infrastructure.print.EscPosCoffee;
import com.illarli.middleware.models.CashDrawer;
import com.illarli.middleware.service.CashDrawerService;
import com.illarli.middleware.service.SendPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cash-drawer")
public class CashDrawerController {

    @Autowired
    private CashDrawerService cashDrawerService;

    @GetMapping("")
    public ResponseEntity<List<CashDrawer>> index() {
        return new ResponseEntity<>(cashDrawerService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CashDrawer cashDrawer) {
        if (cashDrawerService.save(cashDrawer)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody CashDrawer cashDrawer) {
        if (cashDrawerService.save(cashDrawer)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (cashDrawerService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/open-drawer")
    public ResponseEntity<?> openCashDrawer() {
        Optional<CashDrawer> printer = this.cashDrawerService.getAll().stream().findFirst();
        if (printer.isEmpty()) {
            return new ResponseEntity<>("No hay caja guardada", HttpStatus.BAD_REQUEST);
        }
        EscPosCoffee escPosCoffee = new EscPosCoffee(printer.get().getPrinter());
        SendPrintService sendPrintService = new SendPrintService(escPosCoffee);
        if (sendPrintService.openCashDrawer()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
