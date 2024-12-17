package com.bank.userms.Controller;

import com.bank.userms.Dto.*;
import com.bank.userms.Models.User;
import com.bank.userms.Services.*;
import com.bank.userms.Services.Interfaces.AccountIntegrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


    @RestController


    @AllArgsConstructor
    public class UserController {
        private final UserServiceImpl userService;
        private final AccountIntegrationService accountIntegrationService;

        @GetMapping("/users")
        public ResponseEntity<List<User>> getUser() {
            Optional<List<User>> getAllUser = Optional.ofNullable(userService.getAllUsers());
            if(getAllUser.isPresent() && !getAllUser.get().isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(getAllUser.get());}
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        @PostMapping("/users")
        public ResponseEntity<String> addUser(@RequestBody User user) {
            Optional<User> addedUser = Optional.ofNullable(userService.addUser(user)); // Wrap the result in Optional
            // If the user was not found, return BAD REQUEST
            if (!addedUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add user: Invalid user details");
            }

            // If user was successfully updated, return OK
            return ResponseEntity.status(HttpStatus.CREATED).body("User Record is created successfully");
            // return ResponseEntity.created()

        }


        @GetMapping("/users/{id}")
        public ResponseEntity<User> getUser(@PathVariable Long id) throws Exception {
            // Use Optional to safely handle user retrieval
            Optional<User> userOptional = Optional.ofNullable(userService.getUserById(id));

            // Check if the user exists and return appropriate response
            if (userOptional.isPresent()) {
                return new  ResponseEntity<>(userOptional.get(), HttpStatus.OK);
            } else {
                // Return NOT_FOUND if the user does not exist
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        @PutMapping("/users/{id}")
        public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User userDetails) throws Exception {
            Optional<User> updatedUser = Optional.ofNullable(userService.updateUser(id, userDetails));

            // If the user was not found, return BAD REQUEST
            if (!updatedUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update user: Invalid ID or user details");
            }

            // If user was successfully updated, return OK
            return ResponseEntity.ok("User Record is updated successfully");
        }

        @DeleteMapping("/users/{id}")
        public ResponseEntity<String> updateUser(@PathVariable Long id) throws Exception {
            userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User record successfulyy deleted");
        }


//        @PutMapping("users/{userId}/personalinfo")
//        public ResponseEntity< String> updatePersonalInfo(@PathVariable Long userId, @RequestBody  PersonalInfoDTO personalInfo) throws Exception {
//
//            Optional <User > updatedInfo = Optional.ofNullable(userService.updatePersonalInfo(userId, personalInfo));
//           if (!updatedInfo.isPresent()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update user: Invalid ID or user details");
//            }
//
//            // If user was successfully updated, return OK
//            return ResponseEntity.ok("User Record is updated successfully");
////        }
//            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update : Invalid personal -details details");
//        }




//        @GetMapping("users/{userId}/personalinfo")
//        public ResponseEntity<PersonalInfoDTO> getPersonalInfo(@PathVariable Long userId) throws Exception {
//            Optional<User> getuser = Optional.ofNullable(userService.getUserById(userId));
//if (getuser.isPresent()){
//    User user = getuser.get();
//            // Convert User entity to PersonalInfoDTO
//            PersonalInfoDTO personalInfo = new PersonalInfoDTO(
//                    user.getAddress(),
//                    user.getGender(),
//                    user.getEmail()
//            );
//
//            return ResponseEntity.ok(personalInfo); // Return 200 OK with personal inf}
//
//
//            } else {
//                // Return NOT_FOUND if the user does not exist
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
        //}



        @PostMapping("users/{userId}/kyc")
        public ResponseEntity<String> submitKYC(@PathVariable Long userId, @RequestBody KycDto kycDto) {
          KycDto kycDto1 = userService.submitKYC(userId, kycDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("KYC submitted successfully");
        }
        // GET: Retrieve KYC status for a specific user
        @GetMapping("users/{userId}/kyc")
        public ResponseEntity<KycStatusDTO> getKYCStatus(@PathVariable Long userId) {
            KycStatusDTO kycStatus = userService.getKYCStatus(userId);
            return ResponseEntity.ok(kycStatus);
        }
//
        // GET: Retrieve KYC status for all users
        @GetMapping("users/kyc")
        public ResponseEntity<List<KycStatusDTO>> getAllKYCStatuses() {
            List<KycStatusDTO> statuses = userService.getAllKYCStatuses();
            return ResponseEntity.ok(statuses);
        }

       // PUT: Update KYC status for a specific user
        @PutMapping("users/{userId}/kyc")
        public ResponseEntity<String> updateKYCStatus(
                @PathVariable Long userId,
                @RequestBody KycDto kycDto) {

            Optional <KycDto > updatedInfo = Optional.ofNullable( userService.updateKYC(userId,kycDto));
            if (!updatedInfo.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update user: Invalid ID or user details");
            }

            // If user was successfully updated, return OK
            return ResponseEntity.ok("Kyc Record is updated successfully");
//        }
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update : Invalid personal -details details");
        }


        // DELETE: Delete KYC data for a user
        @DeleteMapping("users/{userId}/kyc")
        public ResponseEntity<String> deleteKycData(@PathVariable Long userId) throws Exception {
            userService.deleteKYCData(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User record successfulyy deleted");

        }
        @PostMapping("users/{userId}/accounts")
        public ResponseEntity<LinkAccountResponse> linkAccount(
                @PathVariable Long userId,
                @RequestBody LinkAccountRequest request) {

            // Call the service to link the account
            LinkAccountResponse response = accountIntegrationService.linkAccountToUser(userId, request);

            return ResponseEntity.status(201).body(response);
        }
        @GetMapping("/users/{userId}/accounts")
        public ResponseEntity<List<LinkAllAccountResponse>> getLinkedAccounts(@PathVariable Long userId) {
            // Call the service to get the list of linked accounts
            List<LinkAllAccountResponse> linkedAccounts = accountIntegrationService.getLinkedAccounts(userId);

            return ResponseEntity.ok(linkedAccounts);
        }

    }




//    @ExceptionHandler
//   public ResponseEntity<?> respondWithError(Exception e) {
//    return new ResponseEntity<>("Exception Occurred. More Info :"
//                + e.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//    // Handler for UserNotFoundException
//    @ExceptionHandler
//    public ResponseEntity<?> handleUserNotFoundException(Exception e) {
//        return new ResponseEntity<>("User not found: " + e.getMessage(), HttpStatus.NOT_FOUND);
//    }


