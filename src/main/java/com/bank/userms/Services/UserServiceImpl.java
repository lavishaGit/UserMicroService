package com.bank.userms.Services;

import com.bank.userms.Dto.*;
import com.bank.userms.Mapper.KycMapper;
import com.bank.userms.Models.User;
import com.bank.userms.Models.UserDtoAccount;
import com.bank.userms.Repositories.UserRepository;
import com.bank.userms.Services.Interfaces.UserService;
import com.bank.userms.Mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.String;

/**
 *
 */
@NoArgsConstructor(force = true)
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate;
    private final String someParameter;

//    public UserServiceImpl(RestTemplate restTemplate, @Value("${account.service.url}") String someParameter, UserRepository userRepository) {
//        this.restTemplate = restTemplate;
//        this.someParameter = someParameter;
//        this.userRepository = userRepository;
//    }

    // Base URL for the Account Microservice
   @Value("${account.service.url}")
    public String accountServiceUrl;



    private final UserRepository userRepository;


    private UserMapper userMapper;

    public void someServiceMethod() {
        // Example usage of the accountServiceUrl
        System.out.println("Account service URL: " + accountServiceUrl);
    }
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
            ResponseEntity<UserDtoAccount> response = restTemplate.getForEntity(userServiceUrl, UserDtoAccount.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (Exception e) {
            return false; // User ID does not exist

        }


    }


    //Post call
    @Override
    public LinkAccountResponse linkAccountToUser(Long userId, LinkAccountRequest request) {
        String endpointUrl = accountServiceUrl +"/account";

        // Add userId to the request body
        request.setUserId(userId);

        // Make the POST call to the Account MS
        LinkAccountResponse response = restTemplate.postForObject(endpointUrl, request, LinkAccountResponse.class);

        return response;
    }
    //GetAllAccountofUser
    @Override
    public List<LinkAllAccountResponse> getLinkedAccounts(Long userId) {
        LinkAllAccountResponse[] accountArray = restTemplate.getForObject(
                accountServiceUrl + "/account/user/"+userId,
                LinkAllAccountResponse[].class
        );

        List<LinkAllAccountResponse> accountList = new ArrayList<>();
        if (accountArray != null) {
            for (LinkAllAccountResponse account : accountArray) {
                accountList.add(account);
            }
        }
        System.out.println("Response: " + accountList.toString());
        return accountList;
    }

    @Override
    public LinkSpecificAccountResponse getLinkedAccount(Long userId,Long accountID) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found for the given userId: " + userId);
        }
        LinkSpecificAccountResponse account = restTemplate.getForObject(
                accountServiceUrl + "/account/"+accountID+"/users/"+userId,
                LinkSpecificAccountResponse .class
        );
        return account;
    }
    @Override
    public AccountUpdateResponse updateLinkedAccount(Long userId, Long accountId,AccountUpdateRequest request) {
        // Check if user exists
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found for the given userId: " + userId);
        }
        request.setUserId(userId);
        request.setAccountID(accountId);
        // Call Account-MS to updat
        // ree the account details
        AccountUpdateResponse responseMessage = restTemplate.postForObject(
                accountServiceUrl +"/accounts/"+accountId,request,AccountUpdateResponse.class);


        return responseMessage;
    }
    @Override
    public DeleteAccountResponseDto deleteLinkedAccount(Long userId, Long accountId) {


//The error you're encountering, "Parameter 1 of constructor in com.bank.userms.Services.UserServiceImpl required a bean of type 'java.lang.String' that could not be found", indicates that Spring is trying to inject a String into a constructor in your UserServiceImpl class but couldn't find it.
//
//This error usually happens when Spring expects a bean of a specific type (like String in this case) but doesn't know where to find it.
//
//Likely Cause:
//In the UserServiceImpl class, there may be a constructor that looks like this:
//
//java
//Copy code
//public UserServiceImpl(String accountServiceUrl) {
//    this.accountServiceUrl = accountServiceUrl;
//}
        //ResponseEntity<DeleteAccountResponseDto> response = restTemplate.exchange(url, HttpMethod.DELETE, null, DeleteAccountResponseDto.class);
        try {
            if (userRepository.existsById(userId)) {
                restTemplate.delete(accountServiceUrl + "/accounts/" + accountId);
                return new DeleteAccountResponseDto("Linked account deleted successfully");
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // handle specific HTTP error cases
            return new DeleteAccountResponseDto("Error deleting linked account: " + e.getStatusCode());
        } catch (Exception e) {
            // handle other exceptions
            return new DeleteAccountResponseDto("Error deleting linked account");
        }

        return null;
    }
}

