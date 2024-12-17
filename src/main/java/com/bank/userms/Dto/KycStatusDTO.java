package com.bank.userms.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KycStatusDTO {
    private Long userId;
    private String kycStatus;


}
