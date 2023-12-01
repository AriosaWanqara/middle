package com.illarli.middleware.controllers;

import com.illarli.middleware.models.CashDrawer;
import com.illarli.middleware.service.CashDrawerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
