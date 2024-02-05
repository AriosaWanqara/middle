package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.BalanceRepository;
import com.illarli.middleware.models.Balance;
import com.illarli.middleware.mother.BalanceMother;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
final class BalanceServiceTest {

    @Autowired
    private BalanceRepository balanceRepository;

    @BeforeEach
    void clear() {
        balanceRepository.deleteAll();
    }


    @Test
    void test_should_balance_save_correctly() {
        Balance balance = BalanceMother.create();
        balanceRepository.save(balance);
        Assertions.assertFalse(balanceRepository.findAll().isEmpty(), "list is empty");
    }

    @Test
    void test_should_balance_delete() {
        Balance balance = BalanceMother.create();
        balanceRepository.save(balance);
        Assertions.assertFalse(balanceRepository.findAll().isEmpty(), "list is empty");
        balanceRepository.delete(balance);
        Assertions.assertTrue(balanceRepository.findAll().isEmpty(), "list is not empty");
    }
}