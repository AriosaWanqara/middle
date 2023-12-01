package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.CashDrawerRepository;
import com.illarli.middleware.models.CashDrawer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CashDrawerService {
    @Autowired
    private final CashDrawerRepository cashDrawerRepository;

    public CashDrawerService(CashDrawerRepository cashDrawerRepository) {
        this.cashDrawerRepository = cashDrawerRepository;
    }

    public boolean save(CashDrawer cashDrawer) {
        try {
            cashDrawerRepository.save(cashDrawer);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(String id) {
        try {
            cashDrawerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<CashDrawer> getAll() {
        return cashDrawerRepository.findAll();
    }
}
