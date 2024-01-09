package com.illarli.middleware.command;

import com.illarli.middleware.infrastructure.repositories.BalanceTypeRepository;
import com.illarli.middleware.models.BalanceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BalanceTypeRunner {

    @Autowired
    private final BalanceTypeRepository balanceTypeRepository;

    public BalanceTypeRunner(BalanceTypeRepository balanceTypeRepository) {
        this.balanceTypeRepository = balanceTypeRepository;
    }

    @Bean
    CommandLineRunner balanceTypeSeder() {
        return args -> {
            ArrayList<BalanceType> balanceTypes = new ArrayList<>(List.of(
                    new BalanceType("4fd25d8c-cb94-4743-a6a8-1f4e6e8c7e8d", "CAS PDN", 9600, 8, 1, 0, new byte[]{0x05, 0x11}),
                    new BalanceType("c5d4f149-8dec-4479-9248-74024e3b52aa", "CAS CL 5200", 9600, 8, 1, 0, new byte[]{0x52, 0x34, 0x35, 0x46, 0x30, 0x34, 0x2C, 0x30, 0x30, 0x0A})
            ));
            balanceTypeRepository.saveAll(balanceTypes);
        };
    }

}
