package com.bank.userms.Services.Interfaces;

import com.bank.userms.Dto.KycDto;
import com.bank.userms.Dto.KycStatusDTO;
import com.bank.userms.Dto.PersonalInfoDTO;
import com.bank.userms.Models.User;

import java.util.List;

public interface UserService {
   User addUser(User user);
  List<User> getAllUsers();
    User getUserById(Long userId) throws Exception;
    User updateUser(Long userId, User user) throws Exception;
void deleteUserById(Long userId) throws Exception;
    PersonalInfoDTO createpersonalInfo(Long userId,PersonalInfoDTO personalInfoDTO);
    PersonalInfoDTO getPersonalInfoByUserId(Long userId)throws Exception;
    PersonalInfoDTO updatePersonalInfo(Long userId, PersonalInfoDTO personalInfoDTO) throws Exception;

    KycDto submitKYC(Long userId, KycDto kycDto );

    KycStatusDTO getKYCStatus(Long userId);
    List<KycStatusDTO> getAllKYCStatuses();

    KycDto updateKYC(Long userId, KycDto kycDto);


    // Delete KYC data for a user (but keep the user)
    void deleteKYCData(Long userId);
}
