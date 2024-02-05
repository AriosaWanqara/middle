package com.illarli.middleware.mother;

import com.illarli.middleware.models.Balance;
import com.illarli.middleware.models.BalanceType;

public class BalanceMother {
    public static Balance create() {
        BalanceType balanceType = new BalanceType("4fd25d8c-cb94-4743-a6a8-1f4e6e8c7e8d", "CAS PDN", 9600, 8, 1, 0, new byte[]{0x05, 0x11});
        return new Balance("1", "COM 8", "CAS PDN", 0, balanceType);
    }
}
