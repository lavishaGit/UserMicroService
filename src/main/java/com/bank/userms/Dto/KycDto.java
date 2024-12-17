package com.bank.userms.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KycDto
{
 private long userId;
    private String kycStatus;
    private String kycDocuments;
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
}
