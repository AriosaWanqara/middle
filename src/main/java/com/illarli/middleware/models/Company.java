package com.illarli.middleware.models;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String businessName;
    @Nullable
    private String commercialName;
    private String RUC;
    private boolean forceAccounting = false;
    @Nullable
    private String companyAddress;
    private String subsidiaryAddress;
    @Nullable
    private String companyPhone;
    private String companyEmail;
    private boolean rimpe = false;
    private boolean withholdingAgent = false;
    @Nullable
    private String withholdingAgentResolution;

    protected Company() {
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", businessName='" + businessName + '\'' +
                ", commercialName='" + commercialName + '\'' +
                ", RUC='" + RUC + '\'' +
                ", forceAccounting=" + forceAccounting +
                ", companyAddress='" + companyAddress + '\'' +
                ", subsidiaryAddress='" + subsidiaryAddress + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", companyEmail='" + companyEmail + '\'' +
                ", rimpe=" + rimpe +
                ", withholdingAgent=" + withholdingAgent +
                ", withholdingAgentResolution='" + withholdingAgentResolution + '\'' +
                '}';
    }

    public Company(
            String businessName,
            @Nullable String commercialName,
            String RUC,
            boolean forceAccounting,
            @Nullable String address,
            String subsidiaryAddress,
            @Nullable String phone,
            String email,
            boolean rimpe,
            boolean withholdingAgent,
            @Nullable String withholdingAgentResolution
    ) {
        this.businessName = businessName;
        this.commercialName = commercialName;
        this.RUC = RUC;
        this.forceAccounting = forceAccounting;
        this.companyAddress = address;
        this.subsidiaryAddress = subsidiaryAddress;
        this.companyPhone = phone;
        this.companyEmail = email;
        this.rimpe = rimpe;
        this.withholdingAgent = withholdingAgent;
        this.withholdingAgentResolution = withholdingAgentResolution;
    }

    public String getBusinessName() {
        return businessName;
    }

    @Nullable
    public String getCommercialName() {
        return commercialName;
    }

    public String getRUC() {
        return RUC;
    }

    public boolean isForceAccounting() {
        return forceAccounting;
    }

    @Nullable
    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getSubsidiaryAddress() {
        return subsidiaryAddress;
    }

    @Nullable
    public String getCompanyPhone() {
        return companyPhone;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public boolean isRimpe() {
        return rimpe;
    }

    public boolean isWithholdingAgent() {
        return withholdingAgent;
    }

    @Nullable
    public String getWithholdingAgentResolution() {
        return withholdingAgentResolution;
    }
}
