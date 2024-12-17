package com.bank.userms.Services.Interfaces;

;
import com.bank.userms.Dto.LinkAccountRequest;
import com.bank.userms.Dto.LinkAccountResponse;
import com.bank.userms.Dto.LinkAllAccountResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AccountIntegrationServiceImpl implements AccountIntegrationService {

    private final RestTemplate restTemplate;

    // Base URL for the Account Microservice
    @Value("${account.service.url}")
    private String accountServiceUrl;

    public AccountIntegrationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public LinkAccountResponse linkAccountToUser(Long userId, LinkAccountRequest request) {
        String endpointUrl = accountServiceUrl +"/account";

        // Add userId to the request body
      request.setUserId(userId);

        // Make the POST call to the Account MS
        LinkAccountResponse response = restTemplate.postForObject(endpointUrl, request, LinkAccountResponse.class);

        return response;
    }

    @Override
    public List<LinkAllAccountResponse> getLinkedAccounts(Long userId) {
        String endpointUrl = accountServiceUrl + "/accounts?userId=" + userId;
        // Example: http://account-service/accounts?userId=1

        // Make a GET request to the Account MS
        ResponseEntity<LinkAllAccountResponse[]> responseEntity = restTemplate.getForEntity(endpointUrl, LinkAllAccountResponse[].class);

        // Convert the array response to a list
        LinkAllAccountResponse[] accountArray = responseEntity.getBody();
        return accountArray != null ? Arrays.asList(accountArray) : Collections.emptyList();
    }

}

