package com.bank.userms.Mapper;

import com.bank.userms.Dto.KycDto;
import com.bank.userms.Dto.KycStatusDTO;
import com.bank.userms.Models.User;
import com.bank.userms.enums.KYCStatus;

import java.util.ArrayList;
import java.util.List;

public class KycMapper {

    public static KycDto mapToKycDto(User user) {
        KycDto KycDto = new KycDto();
       KycDto.setUserId(user.getUser_id());
        KycDto.setKycStatus(String.valueOf(user.getKycStatus()));
        KycDto.setKycDocuments(user.getKycDocuments());


        return KycDto;
    }
    public static void updateUserFromDto(User user, KycDto kycDto) {
        user.setKycStatus(KYCStatus.valueOf(kycDto.getKycStatus()));
        user.setKycDocuments(kycDto.getKycDocuments());
        // user.setUser_id(kycDto.getUserId());// not to use this as  primary key (user_id) is being set explicitly:

    }
    public static KycStatusDTO mapToKycStatusDto(User user) {
        // Map the user (or its KYC data) to the DTO
        KycStatusDTO dto = new KycStatusDTO();
        dto.setUserId(user.getUser_id());
        dto.setKycStatus(String.valueOf(user.getKycStatus()));
        return dto;
    }//
    public static List<KycStatusDTO> mapToKycStatusDto(List<User> users) {
        List<KycStatusDTO> dtoList = new ArrayList<>();

        for (User user : users) {
            KycStatusDTO dto = new KycStatusDTO();
            dto.setUserId(user.getUser_id());
            dto.setKycStatus(String.valueOf(user.getKycStatus()));
            dtoList.add(dto);
        }

        return dtoList;
    }
//This method is intended to convert data from a DTO to update a User entity, typically used in a POST or PUT call to modify the database.
//    public static void getUserFromDto(User user, KycDto kycDto) {
//        user.setKycStatus(KYCStatus.valueOf(kycDto.getKycStatus()));
//    }


//    public static User mapToUser(KycDto kycDto) {
//     User user = new User();
//      //  user.setUser_id(kycDto.getUserId());
//        user.setKycStatus(KYCStatus.valueOf(kycDto.getKycStatus()));
//        user.setKycDocuments(kycDto.getKycDocuments());
//
//
//        return user;
//    }
}
