package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.CashDrawerRepository;
import com.illarli.middleware.models.CashDrawer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashDrawerService {
    @Autowired
    private final CashDrawerRepository cashDrawerRepository;
    private final Logger logger = LoggerFactory.getLogger(CashDrawerService.class);

    public CashDrawerService(CashDrawerRepository cashDrawerRepository) {
        this.cashDrawerRepository = cashDrawerRepository;
    }

    public boolean save(CashDrawer cashDrawer) {
        try {
            cashDrawerRepository.save(cashDrawer);
            return true;
        } catch (Exception e) {
            logger.warn("Error save cash drawer");
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public boolean delete(String id) {
        try {
            cashDrawerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.warn("Error delete cash drawer");
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public List<CashDrawer> getAll() {
        return cashDrawerRepository.findAll();
    }
}
