package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.CashDrawerRepository;
import com.illarli.middleware.infrastructure.repositories.PrinterRepository;
import com.illarli.middleware.models.CashDrawer;
import com.illarli.middleware.mother.CashDrawerMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CashDrawerServiceTest {
    @Autowired
    private CashDrawerRepository cashDrawerRepository;
    @Autowired
    private PrinterRepository printerRepository;

    @BeforeEach
    void clear() {
        CashDrawerMother.createPrinter(printerRepository);
        cashDrawerRepository.deleteAll();
    }

    @Test
    void test_should_delete_correctly() {
        CashDrawer entity = CashDrawerMother.create();
        cashDrawerRepository.save(entity);
        Assertions.assertFalse(cashDrawerRepository.findAll().isEmpty(), "List is empty");
        cashDrawerRepository.delete(entity);
        Assertions.assertTrue(cashDrawerRepository.findAll().isEmpty(), "List is not empty");
    }

    @Test
    void test_should_save_and_list_correctly() {
        CashDrawer entity = CashDrawerMother.create();
        cashDrawerRepository.save(entity);
        Assertions.assertFalse(cashDrawerRepository.findAll().isEmpty(), "List is empty");
    }
}