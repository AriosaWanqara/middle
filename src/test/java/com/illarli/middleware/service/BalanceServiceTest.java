package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.BalanceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
final class BalanceServiceTest {

    @Autowired
    private BalanceRepository balanceRepository;

    @Test
    void getAll() {
        Assertions.assertTrue(balanceRepository.findAll().isEmpty(), "list is not empty");
    }

    @Test
    void getComList() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}