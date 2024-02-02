package com.illarli.middleware.service;

import com.illarli.middleware.infrastructure.repositories.BalanceRepository;
import com.illarli.middleware.models.Balance;
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

    @BeforeEach
    void clear() {
        balanceRepository.deleteAll();
    }


    @Test
    void should_balance_save_correctly() {
        BalanceType balanceType = new BalanceType("4fd25d8c-cb94-4743-a6a8-1f4e6e8c7e8d", "CAS PDN", 9600, 8, 1, 0, new byte[]{0x05, 0x11});
        Balance balance = new Balance("1", "COM 8", "CAS PDN", 0, balanceType);
        balanceRepository.save(balance);
        Assertions.assertTrue(balanceRepository.findAll().size() > 0, "list is empty");
    }

    @Test
    void should_balance_delete() {
        BalanceType balanceType = new BalanceType("4fd25d8c-cb94-4743-a6a8-1f4e6e8c7e8d", "CAS PDN", 9600, 8, 1, 0, new byte[]{0x05, 0x11});
        Balance balance = new Balance("1", "COM 8", "CAS PDN", 0, balanceType);
        balanceRepository.save(balance);
        Assertions.assertTrue(balanceRepository.findAll().size() > 0, "list is empty");
        balanceRepository.delete(balance);
        Assertions.assertTrue(balanceRepository.findAll().isEmpty(), "list is not empty");
    }
}