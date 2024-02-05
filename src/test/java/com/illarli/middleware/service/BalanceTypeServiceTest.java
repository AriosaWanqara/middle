package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.BalanceTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BalanceTypeServiceTest {

    @Autowired
    private BalanceTypeRepository balanceTypeRepository;

    @Test
    void test_ensure_balance_type_list_is_not_empty() {
        Assertions.assertFalse(balanceTypeRepository.findAll().isEmpty(), "List is empty");
    }
}