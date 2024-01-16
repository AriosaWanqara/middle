package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.TagPrinterRepository;
import com.illarli.middleware.models.TagPrinter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagPrinterService {
    private final TagPrinterRepository tagPrinterRepository;

    public TagPrinterService(TagPrinterRepository tagPrinterRepository) {
        this.tagPrinterRepository = tagPrinterRepository;
    }

    public List<TagPrinter> getAll() {
        return this.tagPrinterRepository.findAll();
    }
}
