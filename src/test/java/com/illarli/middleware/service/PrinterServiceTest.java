package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.PrinterRepository;
import com.illarli.middleware.models.DocumentTypeEnum;
import com.illarli.middleware.models.Printer;
import com.illarli.middleware.mother.PrinterMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PrinterServiceTest {

    @Autowired
    private PrinterRepository printerRepository;

    @BeforeEach
    void clear() {
        printerRepository.deleteAll();
    }

    @Test
    void test_print_save_and_list_correctly() {
        Printer printer = PrinterMother.create(null);
        printerRepository.save(printer);
        Assertions.assertFalse(printerRepository.findAll().isEmpty(), "List is empty");
    }

    @Test
    void test_that_save_printer_is_present() {
        Printer printer = PrinterMother.create(null);
        printerRepository.save(printer);
        Assertions.assertTrue(printerRepository.findById("1").isPresent(), "Printer not found by id");
    }

    @Test
    void test_that_save_printer_is_not_present_with_different_id() {
        Printer printer = PrinterMother.create(null);
        printerRepository.save(printer);
        Assertions.assertFalse(printerRepository.findById("2").isPresent(), "Printer not found by id");
    }

    @Test
    void test_that_save_printer_is_present_by_document_code() {
        Printer printer = PrinterMother.create(null);
        printerRepository.save(printer);
        Assertions.assertTrue(
                printerRepository.findByDocumentType_code(DocumentTypeEnum.ELECTRONIC_INVOICE.getCode()).isPresent(),
                "Printer not found by document code"
        );
    }

    @Test
    void test_that_save_printer_is_not_present_by_different_document_code() {
        Printer printer = PrinterMother.create(null);
        printerRepository.save(printer);
        Assertions.assertFalse(
                printerRepository.findByDocumentType_code(DocumentTypeEnum.VOUCHER.getCode()).isPresent(),
                "Printer not found by document code"
        );
    }

    @Test
    void test_delete_printer_by_id() {
        Printer printer = PrinterMother.create(null);
        printerRepository.save(printer);
        printerRepository.deleteById("1");
        Assertions.assertTrue(printerRepository.findAll().isEmpty(), "List is not empty");
    }
}