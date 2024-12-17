package com.bank.userms.Mapper;

import com.bank.userms.Dto.PersonalInfoDTO;
import com.bank.userms.Models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Update only allowed fields in the User entity
    void updateUserFromDto(PersonalInfoDTO dto, @MappingTarget User user);
}


