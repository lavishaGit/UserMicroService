package com.bank.userms.Dto;

import java.time.LocalDateTime;

public class LinkAllAccountResponse {
    private Long userId;
    private Long accountId;

    private String accountNumber;
    private String accountType;
    private Double balance;
    private String currency;
    private LocalDateTime createdAt;
}
