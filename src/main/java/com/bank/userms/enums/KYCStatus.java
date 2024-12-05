package com.bank.userms.enums;
public enum KYCStatus {
    PENDING,  // Default status when a user is created
    VERIFIED, // Status when the KYC is successfully completed
    REJECTED  // Status when the KYC is rejected due to invalid or incomplete information
}
