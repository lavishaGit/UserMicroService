package com.bank.userms.Services.Interfaces;


import com.bank.userms.Dto.LinkAccountRequest;
import com.bank.userms.Dto.LinkAccountResponse;
import com.bank.userms.Dto.LinkAllAccountResponse;

import java.util.List;

public interface AccountIntegrationService {
    LinkAccountResponse linkAccountToUser(Long userId, LinkAccountRequest request);
    public List<LinkAllAccountResponse> getLinkedAccounts(Long userId);

}

