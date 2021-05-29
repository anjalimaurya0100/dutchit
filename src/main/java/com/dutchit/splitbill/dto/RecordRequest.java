package com.dutchit.splitbill.dto;


import com.dutchit.splitbill.model.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class RecordRequest {
    private Long fromCustomer;

    private Long toCustomer;

    @NotNull
    private double amount;
}
