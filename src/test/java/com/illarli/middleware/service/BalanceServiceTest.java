package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.BalanceRepository;
import com.illarli.middleware.infrastructure.repositories.BalanceTypeRepository;
import com.illarli.middleware.models.BalanceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
final class BalanceServiceTest {

    @Autowired
    private BalanceRepository balanceRepository;
    @Autowired
    private BalanceTypeRepository balanceTypeRepository;

    @BeforeEach
    void clear() {
        balanceRepository.deleteAll();
        BalanceType balanceType = new BalanceType("4fd25d8c-cb94-4743-a6a8-1f4e6e8c7e8d", "CAS PDN", 9600, 8, 1, 0, new byte[]{0x05, 0x11});
        balanceTypeRepository.save(balanceType);
    }


    @Test
    void getAll() {
        Assertions.assertTrue(balanceRepository.findAll().isEmpty(), "list is not empty");
    }

    @Test
    void save() {
        Assertions.assertTrue(balanceRepository.findAll().isEmpty(), "List is not empty");
    }

    @Test
    void delete() {
        Assertions.assertTrue(balanceRepository.findAll().isEmpty(), "save doesn't work");
    }
}