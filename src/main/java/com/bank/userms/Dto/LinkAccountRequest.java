package com.bank.userms.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkAccountRequest {
    private Long userId; // Add userId for linking
    private String accountNumber;
    private String accountType;
    private Double balance;
    private String currency;
//    {
//        "userId":5,
//            "accountNumber": "3456759",
//            "accountType": "others",
//            "currency":"USD",
//            "balance":1000
//    }
//{

//
//    "accountNumber": "987654321",
//        "accountType": "others1",
//        "balance": 1000.50,
//        "currency": "USD"
//
//}
    // Getters and Setters
}

