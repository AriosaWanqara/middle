package com.illarli.middleware.controllers;

import com.illarli.middleware.models.BalanceType;
import com.illarli.middleware.service.BalanceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/balance-type")
public class BalanceTypeController {

    @Autowired
    private BalanceTypeService balanceTypeService;

    @GetMapping("/list")
    public ResponseEntity<List<BalanceType>> index(){
        return new ResponseEntity<>(balanceTypeService.getAll(), HttpStatus.OK);
    }
}
