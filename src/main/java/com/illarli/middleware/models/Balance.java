package com.illarli.middleware.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Balance {
    @Id
    private String id;
    private String port;
    private String name;
    private int getWeightTimer;
    @ManyToOne()
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return getWeightTimer == balance.getWeightTimer && Objects.equals(id, balance.id) && Objects.equals(port, balance.port) && Objects.equals(name, balance.name) && Objects.equals(balanceType, balance.balanceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, port, name, getWeightTimer, balanceType);
    }
}
