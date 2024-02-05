package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.TagPrinterRepository;
import com.illarli.middleware.models.TagPrinter;
import com.illarli.middleware.mother.TagPrinterMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TagPrinterServiceTest {
    @Autowired
    private TagPrinterRepository tagPrinterRepository;

    @BeforeEach
    void clear() {
        tagPrinterRepository.deleteAll();
    }

    @Test
    void test_tag_printer_is_save_and_list_correctly() {
        TagPrinter tagPrinter = TagPrinterMother.create();
        tagPrinterRepository.save(tagPrinter);
        Assertions.assertFalse(tagPrinterRepository.findAll().isEmpty(), "List is empty");
    }
}