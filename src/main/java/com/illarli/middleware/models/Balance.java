package com.illarli.middleware.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Balance {
    @Id
    private String id;
    private String port;
    private String name;
    private int getWeightTimer;
    @OneToOne
    @JoinColumn(name = "balanceType", referencedColumnName = "id")
    private BalanceType balanceType;

    public Balance(String id, String port, String name, int getWeightTimer, BalanceType balanceType) {
        this.id = id;
        this.port = port;
        this.name = name;
        this.getWeightTimer = getWeightTimer;
        this.balanceType = balanceType;
    }

    protected Balance() {
    }

    public String getId() {
        return id;
    }

    public String getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public int getGetWeightTimer() {
        return getWeightTimer;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }
}
