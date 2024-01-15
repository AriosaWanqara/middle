package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.BalanceRepository;
import com.illarli.middleware.models.Balance;
import com.illarli.middleware.models.repositories.BalanceLibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceService {
    @Autowired
    private final BalanceRepository balanceRepository;
    private final Logger logger = LoggerFactory.getLogger(BalanceService.class);

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public List<Balance> getAll() {
        return balanceRepository.findAll();
    }

    public List<String> getComList(BalanceLibraryRepository balanceLibraryRepository) {
        return balanceLibraryRepository.getComList();
    }

    public boolean save(Balance balance) {
        try {
            balanceRepository.save(balance);
            return true;
        } catch (Exception e) {
            logger.warn("Error save balance");
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public boolean delete(String id) {
        try {
            balanceRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            logger.warn("Error delete balance");
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}
