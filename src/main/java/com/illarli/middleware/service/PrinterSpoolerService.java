package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.ClientRepository;
import com.illarli.middleware.infrastructure.repositories.CompanyRepository;
import com.illarli.middleware.infrastructure.repositories.DetailsRepository;
import com.illarli.middleware.infrastructure.repositories.PrinterSpoolerRepository;
import com.illarli.middleware.models.PrinterSpooler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterSpoolerService {
    @Autowired
    private final PrinterSpoolerRepository printerSpoolerRepository;
    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    private final CompanyRepository companyRepository;
    @Autowired
    private final DetailsRepository detailsRepository;

    public PrinterSpoolerService(PrinterSpoolerRepository printerSpoolerRepository, ClientRepository clientRepository, CompanyRepository companyRepository, DetailsRepository detailsRepository) {
        this.printerSpoolerRepository = printerSpoolerRepository;
        this.clientRepository = clientRepository;
        this.companyRepository = companyRepository;
        this.detailsRepository = detailsRepository;
    }

    public void save(PrinterSpooler printerSpooler) {
        try {
            this.printerSpoolerRepository.save(printerSpooler);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<PrinterSpooler> getAll() {
        return this.printerSpoolerRepository.findAll();
    }

    public void removeAllByIds(List<String> ids) {
        this.printerSpoolerRepository.deleteAllById(ids);
    }

    public void removeById(String id) {
        this.printerSpoolerRepository.deleteById(id);
    }
}
