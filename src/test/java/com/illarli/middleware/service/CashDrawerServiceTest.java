package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.CashDrawerRepository;
import com.illarli.middleware.infrastructure.repositories.PrinterRepository;
import com.illarli.middleware.models.CashDrawer;
import com.illarli.middleware.models.Printer;
import com.illarli.middleware.models.PrinterType;
import com.illarli.middleware.mother.CashDrawerMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;

@SpringBootTest
class CashDrawerServiceTest {
    @Autowired
    private CashDrawerRepository cashDrawerRepository;
    @Autowired
    private PrinterRepository printerRepository;

    @BeforeEach
    void clear() {
        Printer printer = new Printer
                (
                        "ad",
                        "null",
                        "null",
                        1,
                        1,
                        null,
                        1,
                        1,
                        PrinterType.WIFI,
                        new LinkedList<>()
                );
        printerRepository.save(printer);
        cashDrawerRepository.deleteAll();
    }

    @Test
    void should_delete_correctly() {
        CashDrawer entity = CashDrawerMother.create();
        cashDrawerRepository.save(entity);
        Assertions.assertTrue(cashDrawerRepository.findAll().size() > 0, "List is empty");
        cashDrawerRepository.delete(entity);
        Assertions.assertTrue(cashDrawerRepository.findAll().isEmpty(), "List is not empty");
    }

    @Test
    void should_save_and_list_correctly() {
        CashDrawer entity = CashDrawerMother.create();
        cashDrawerRepository.save(entity);
        Assertions.assertTrue(cashDrawerRepository.findAll().size() > 0, "List is empty");
    }
}