package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.TagPrinterRepository;
import com.illarli.middleware.models.TagPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagPrinterService {
    private final TagPrinterRepository tagPrinterRepository;
    private final Logger logger = LoggerFactory.getLogger(TagPrinterService.class);

    public TagPrinterService(TagPrinterRepository tagPrinterRepository) {
        this.tagPrinterRepository = tagPrinterRepository;
    }

    public List<TagPrinter> getAll() {
        return this.tagPrinterRepository.findAll();
    }

    public boolean save(TagPrinter tagPrinter) {
        try {
            this.tagPrinterRepository.deleteAll();
            this.tagPrinterRepository.save(tagPrinter);
            return true;
        } catch (Exception e) {
            logger.warn("Error save tag printer");
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}
