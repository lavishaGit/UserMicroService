package com.bank.userms.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountUpdateRequest {
    private Long userId;
    private Long accountID;

    private String accountType;
    private Double balance;
    private String currency;
}
