package com.laptrinhjavaweb.dto.response;

import com.laptrinhjavaweb.entity.TransactionEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse extends BaseResponse{
    private Long typeId;
    private String note;
    private String code;

    public TransactionResponse(TransactionEntity entity) {
        this.typeId = entity.getTransactionType().getId();
        this.note = entity.getNote();
        this.setCreatedDate(entity.getCreatedDate());
        this.code = entity.getTransactionType().getCode();
    }
}
