package com.illarli.middleware.service;


import com.illarli.middleware.infrastructure.repositories.BalanceTypeRepository;
import com.illarli.middleware.models.BalanceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceTypeService {

    @Autowired
    private final BalanceTypeRepository balanceTypeRepository;

    public BalanceTypeService(BalanceTypeRepository balanceTypeRepository) {
        this.balanceTypeRepository = balanceTypeRepository;
    }

    public List<BalanceType> getAll() {
        return balanceTypeRepository.findAll();
    }
}
