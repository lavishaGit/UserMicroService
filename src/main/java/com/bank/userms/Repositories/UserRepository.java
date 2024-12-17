package com.bank.userms.Repositories;

import com.bank.userms.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    // Custom query method to find personal info by userId
  //  PersonalInfo findByUserId(Long userId);
}
