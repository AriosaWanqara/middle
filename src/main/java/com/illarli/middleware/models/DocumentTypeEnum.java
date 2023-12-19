package com.illarli.middleware.models;

public enum DocumentTypeEnum {
    ELECTRONIC_INVOICE("01"),
    PRE_TICKET("02"),
    VOUCHER("03"),
    QUOTATION("04"),
    COMMAND("05"),
    CLOSE_DRAWER("06");
    private String code;

    private DocumentTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
