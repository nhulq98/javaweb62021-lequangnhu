package com.laptrinhjavaweb.enums;

public enum TransactionTypeEnum {
    QUA_TRINH_CSKH("QUÁ TRÌNH CSKH"),
    DAN_DI_XEM("DẪN ĐI XEM");
    //CHAM_SOC_DAC_BIET("CHĂM SÓC ĐẶC BIỆT");

    private final String transactionValue;

    TransactionTypeEnum(String transactionValue) {
        this.transactionValue = transactionValue;
    }

    public String getDistrictValue() {
        return transactionValue;
    }
}
