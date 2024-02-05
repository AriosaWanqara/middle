package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.PrinterSpoolerRepository;
import com.illarli.middleware.models.PrinterSpooler;
import com.illarli.middleware.mother.PrinterSpoolerMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PrinterSpoolerServiceTest {
    @Autowired
    private PrinterSpoolerRepository printerSpoolerRepository;

    @BeforeEach
    void clear() {
        printerSpoolerRepository.deleteAll();
    }

    @Test
    void test_printer_spooler_save_and_list_correctly() {
        PrinterSpooler printerSpooler = PrinterSpoolerMother.create(null);
        printerSpoolerRepository.save(printerSpooler);
        Assertions.assertTrue(printerSpoolerRepository.findAll().size() > 0, "List is empty");
    }

    @Test
    void test_than_remove_from_array_of_id_delete_correctly() {
        PrinterSpooler printerSpooler = PrinterSpoolerMother.create(null);
        PrinterSpooler printerSpooler2 = PrinterSpoolerMother.create("2");
        PrinterSpooler printerSpooler3 = PrinterSpoolerMother.create("3");
        printerSpoolerRepository.save(printerSpooler);
        printerSpoolerRepository.save(printerSpooler2);
        printerSpoolerRepository.save(printerSpooler3);
        List<String> ids = new ArrayList<>() {{
            add("1");
            add("2");
        }};
        printerSpoolerRepository.deleteAllById(ids);
        Assertions.assertTrue(printerSpoolerRepository.findAll().size() > 0, "List is empty");
        boolean check = printerSpoolerRepository.findAll().stream().noneMatch(printerSpooler1 -> {
            return ids.contains(printerSpooler1.getId());
        });
        Assertions.assertTrue(check, "printer spooler with delete id still existing");
    }
}