package com.illarli.middleware.controllers;


import com.illarli.middleware.models.Balance;
import com.illarli.middleware.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @GetMapping("")
    public ResponseEntity<List<Balance>> index() {
        return new ResponseEntity<>(balanceService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Balance balance) {
        if (balanceService.save(balance)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        if (balanceService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>("The id " + id + " does not exist", HttpStatus.BAD_REQUEST);
    }
}
