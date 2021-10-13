package com.laptrinhjavaweb.enums;

public enum TransactionTypeEnum {
    QUA_TRINH_CSKH("QUÁ TRÌNH CSKH"),
    DAN_DI_XEM("DẪN ĐI XEM");

    private final String transactionValue;

    TransactionTypeEnum(String transactionValue) {
        this.transactionValue = transactionValue;
    }

    public String getDistrictValue() {
        return transactionValue;
    }
}
