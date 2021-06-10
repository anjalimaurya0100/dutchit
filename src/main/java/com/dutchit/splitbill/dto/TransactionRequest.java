package com.dutchit.splitbill.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TransactionRequest {

    private long toCustomer;
    private long fromCustomer;

    @NotNull
    private double amount;
}
