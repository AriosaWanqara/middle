package com.illarli.middleware.service;

import com.illarli.middleware.impl.PrintLibraryMockImpl;
import com.illarli.middleware.models.repositories.PrinterLibraryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SendPrintServiceTest {
    private final PrinterLibraryRepository printLibraryRepository = new PrintLibraryMockImpl();

    @Test
    void test_should_print_test_success() {
        Assertions.assertTrue(printLibraryRepository.printTest(), "Print test goes good");
    }

    @Test
    void test_should_print_test_fail() {
        Assertions.assertFalse(!printLibraryRepository.printTest(), "Print test goes wrong");
    }

    @Test
    void test_should_print_text_success() {
        Assertions.assertTrue(printLibraryRepository.printTest(), "Print text goes good");
    }

    @Test
    void test_should_print_text_fail() {
        Assertions.assertFalse(!printLibraryRepository.printTest(), "Print text goes wrong");
    }

    @Test
    void test_should_print_title_success() {
        Assertions.assertTrue(printLibraryRepository.printTest(), "Print title goes good");
    }

    @Test
    void test_should_print_title_fail() {
        Assertions.assertFalse(!printLibraryRepository.printTest(), "Print title goes wrong");
    }

    @Test
    void test_should_cut_success() {
        Assertions.assertTrue(printLibraryRepository.printTest(), "cut goes good");
    }

    @Test
    void test_should_cut_fail() {
        Assertions.assertFalse(!printLibraryRepository.printTest(), "cut goes wrong");
    }

    @Test
    void test_should_open_cash_drawer_open_success() {
        Assertions.assertTrue(printLibraryRepository.printTest(), "open cash drawer goes good");
    }

    @Test
    void test_should_open_cash_drawer_open_fail() {
        Assertions.assertFalse(!printLibraryRepository.printTest(), "open cash drawer goes wrong");
    }

    @Test
    void test_should_print_cash_drawer_details_success() {
        Assertions.assertTrue(printLibraryRepository.printTest(), "Print cash drawer details goes good");
    }

    @Test
    void test_should_print_cash_drawer_details_fail() {
        Assertions.assertFalse(!printLibraryRepository.printTest(), "Print cash drawer details goes wrong");
    }

    @Test
    void test_should_print_voucher_success() {
        Assertions.assertTrue(printLibraryRepository.printTest(), "Print voucher goes good");
    }

    @Test
    void test_should_print_voucher_fail() {
        Assertions.assertFalse(!printLibraryRepository.printTest(), "Print voucher goes wrong");
    }

    @Test
    void test_should_print_electronic_invoice_success() {
        Assertions.assertTrue(printLibraryRepository.printTest(), "Print electronic invoice goes good");
    }

    @Test
    void test_should_print_electronic_invoice_fail() {
        Assertions.assertFalse(!printLibraryRepository.printTest(), "Print electronic invoice goes wrong");
    }

    @Test
    void test_should_pre_ticket_success() {
        Assertions.assertTrue(printLibraryRepository.printTest(), "Print pre ticket goes good");
    }

    @Test
    void test_should_pre_ticket_fail() {
        Assertions.assertFalse(!printLibraryRepository.printTest(), "Print pre ticket goes wrong");
    }

    @Test
    void test_should_command_invoice_success() {
        Assertions.assertTrue(printLibraryRepository.printTest(), "Print command goes good");
    }

    @Test
    void test_should_command_invoice_fail() {
        Assertions.assertFalse(!printLibraryRepository.printTest(), "Print command goes wrong");
    }


}