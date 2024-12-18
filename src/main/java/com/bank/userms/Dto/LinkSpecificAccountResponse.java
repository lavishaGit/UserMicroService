package com.bank.userms.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkSpecificAccountResponse {
    private Long userId;
    private Long accountID;

    private String accountNumber;
    private String accountType;
    private String currency;
    private Double balance;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createdAt;

//    {
//        "userId": 9,
//            "accountID": 27,
//            "accountNumber": "3457",
//            "accountType": "saving",
//            "currency": "USD",
//            "balance": 8000.0,
//            "createdAt": "2024-12-16T17:15:11Z"
//    }
}
