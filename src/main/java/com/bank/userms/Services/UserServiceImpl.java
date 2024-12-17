package com.bank.userms.Services;

import com.bank.userms.Dto.KycDto;
import com.bank.userms.Dto.KycStatusDTO;
import com.bank.userms.Dto.PersonalInfoDTO;
import com.bank.userms.Mapper.KycMapper;
import com.bank.userms.Models.User;
import com.bank.userms.Models.UserDtoAccount;
import com.bank.userms.Repositories.UserRepository;
import com.bank.userms.Services.Interfaces.UserService;
import com.bank.userms.Mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 *
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate;

    private final UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(() -> new Exception("User not found" + userId));

    }

    @Override
    public User updateUser(Long userId, User user) throws Exception {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found" + userId));
        // Update the user's fields with the new values
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(existingUser);
    }


    @Override
    public void deleteUserById(Long userId) throws Exception {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found" + userId));

        userRepository.deleteById(userId);

    }

    @Override
    public PersonalInfoDTO createpersonalInfo(Long userId, PersonalInfoDTO personalInfoDTO) {
        return null;
    }

    @Override
    public PersonalInfoDTO getPersonalInfoByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public PersonalInfoDTO updatePersonalInfo(Long userId, PersonalInfoDTO personalInfoDTO) throws Exception {
        return null;
    }


//    @Override
//    public PersonalInfoDTO  updatePersonalInfo(Long userId, PersonalInfoDTO personalInfoDTO) throws Exception {
//        Optional<User> updatedInfo = userRepository.findById(userId);
//
//        if (updatedInfo.isPresent()) {
//            User user = updatedInfo.get();
//            System.out.println(user);
    //// Use the mapper to update only the desired fields
//            // userMapper.updateUserFromDto(personalInfoDTO, user);
//            // Update only the fields that are not null
//            if (personalInfoDTO.getEmail() != null) {
//                user.setEmail(personalInfoDTO.getEmail());
//            }
//            if (personalInfoDTO.getAddress() != null) {
//                user.setAddress(personalInfoDTO.getAddress());
//            }
//            if (personalInfoDTO.getGender() != null) {
//                user.setGender(personalInfoDTO.getGender());
//            }
//
//
//            return userRepository.save(user);
//        }
//
//        throw new Exception("User not found with ID: " + userId);
//    }

//    @Override
//    public  PersonalInfoDTO  updatePersonalInfo(Long userId, PersonalInfoDTO personalInfoDTO) throws Exception {
//        Optional<User> updatedInfo = userRepository.findById(userId);
//
//        if (updatedInfo.isPresent()) {
//            User user = updatedInfo.get();
//            System.out.println(user);
//            // Use the mapper to update only the desired fields
//            // userMapper.updateUserFromDto(personalInfoDTO, user);
//            // Update only the fields that are not null
//            if (personalInfoDTO.getEmail() != null) {
//                user.setEmail(personalInfoDTO.getEmail());
//            }
//            if (personalInfoDTO.getAddress() != null) {
//                user.setAddress(personalInfoDTO.getAddress());
//            }
//            if (personalInfoDTO.getGender() != null) {
//                user.setGender(personalInfoDTO.getGender());
//            }
//
//
//            return userRepository.save(user);
//        }

//        throw new Exception("User not found with ID: " + userId);
//    }


    // KYC Management
    @Override
    public KycDto submitKYC(Long userId, KycDto kycDto ) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            KycMapper.updateUserFromDto(user, kycDto); // Update fields
//                user.setKycStatus(KYCStatus.valueOf(kycDto.getKycStatus()));
//               // user.setUser_id(kycDto.getUserId());
//                user.setKycDocuments(kycDto.getKycDocuments());
            user = userRepository.save(user);
            return KycMapper.mapToKycDto(user);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    @Override
    public KycStatusDTO getKYCStatus(Long userId) {
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent()){
            User userDetails = user.get();
            // KycMapper.mapToKycStatusDto(userDetails);
            return KycMapper.mapToKycStatusDto(userDetails);
        }else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

    }

    @Override
    public List<KycStatusDTO> getAllKYCStatuses() {
        List<User> users = userRepository.findAll();  // No need to wrap in Optional
        if (users.isEmpty()) {
            throw new IllegalArgumentException("No users found.");
        }

        // Map the list of users to KYC status DTOs
        return KycMapper.mapToKycStatusDto(users);
    }

    @Override
    public KycDto updateKYC(Long userId, KycDto kycDto) {
 Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User getuser = userOptional.get();

            // Map the fields from KYC DTO to User entity
            KycMapper.updateUserFromDto(getuser, kycDto);

            // Save the updated user back to the repository
            userRepository.save(getuser);

            // Map the updated user entity back to KYC DTO
            return KycMapper.mapToKycDto(getuser);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }

    @Override
    public void deleteKYCData(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Nullify KYC-related data (you can modify this according to your requirements)
            user.setKycStatus(null);  // Removing the KYC status
            user.setKycDocuments(null);  // Removing the KYC documents

            // Save the user back to the repository after clearing the KYC data
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }
    public boolean validateUserId(Long userId) {
        String userServiceUrl = "http://user-ms/users/" + userId;
        try {
            ResponseEntity<UserDtoAccount> response = restTemplate.getForEntity(userServiceUrl,UserDtoAccount.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false; // User ID does not exist

    }}
}

