package com.bank.userms.Models;

import com.bank.userms.enums.KYCStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "Users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(unique = true, nullable = false)

    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(name = "phone_number", unique = true, length = 15)
    private String phoneNumber;
    @Column(name = "two_factor_enabled", nullable = false)
    private Boolean twoFactorEnabled = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "kyc_status", nullable = false)
    private KYCStatus kycStatus = KYCStatus.PENDING;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)

    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @Column(name = "address", nullable = false)
    private String address;
      @Column(name = "gender", nullable = false)
      private String gender;

    @Column(name = "kyc_documents", length = 500,nullable = false)
    private String kycDocuments;

    @Column(name = "kyc_comments", length = 500)
    private String kycComments;
}
