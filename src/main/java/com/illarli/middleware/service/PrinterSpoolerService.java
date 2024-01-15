package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.PrinterSpoolerRepository;
import com.illarli.middleware.models.PrinterSpooler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterSpoolerService {
    @Autowired
    private final PrinterSpoolerRepository printerSpoolerRepository;
    private final Logger logger = LoggerFactory.getLogger(PrinterSpoolerService.class);

    public PrinterSpoolerService(PrinterSpoolerRepository printerSpoolerRepository) {
        this.printerSpoolerRepository = printerSpoolerRepository;
    }

    public void save(PrinterSpooler printerSpooler) {
        try {
            this.printerSpoolerRepository.save(printerSpooler);
        } catch (Exception e) {
            logger.warn("Error printer spooler service");
            logger.error(e.getMessage(), e);
        }
    }

    public List<PrinterSpooler> getAll() {
        return this.printerSpoolerRepository.findAll();
    }

    public void removeAllByIds(List<String> ids) {
        try {
            this.printerSpoolerRepository.deleteAllById(ids);
        } catch (Exception e) {
            logger.warn("Error remove all printer spooler");
            logger.error(e.getMessage(), e);
        }
    }

}
