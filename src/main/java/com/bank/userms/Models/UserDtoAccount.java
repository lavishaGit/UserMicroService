package com.bank.userms.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoAccount {
    private Long userId;
    private String name;
    private String email;
}

