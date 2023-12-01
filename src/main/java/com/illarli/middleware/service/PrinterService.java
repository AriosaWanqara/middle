package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.PrinterRepository;
import com.illarli.middleware.models.DocumentType;
import com.illarli.middleware.models.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrinterService {

    @Autowired
    private final PrinterRepository printerRepository;

    public PrinterService(PrinterRepository printerRepository) {
        this.printerRepository = printerRepository;
    }

    public boolean save(Printer printer) {
        try {
            List<Printer> printers = printerRepository.findAll();
            List<Printer> printersToDeleteFromInMemoryList = new ArrayList<>(List.of());
            printers.removeIf(printer1 -> printer1.getId().equals(printer.getId()));
            for (Printer pr : printers) {
                for (DocumentType documentType : printer.getDocumentType()) {
                    pr.getDocumentType().removeIf(documentType1 -> documentType1.getId().equals(documentType.getId()));
                }
                if (pr.getDocumentType().isEmpty()) {
                    printerRepository.deleteById(pr.getId());
                    printersToDeleteFromInMemoryList.add(pr);
                }
            }
            for (Printer printer1 : printersToDeleteFromInMemoryList) {
                printers.removeIf(printer2 -> printer2.getId().equals(printer1.getId()));
            }
            printers.add(printer);
            printerRepository.saveAll(printers);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public List<Printer> getAll() {
        return printerRepository.findAll();
    }

    public Optional<Printer> getPrinterById(String id) {
        return printerRepository.findById(id);
    }

    public boolean delete(String id) {
        try {

            printerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
