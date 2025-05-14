package com.accountapi.service.account;

import com.accountapi.dto.transfer.TransferRequestDto;

public interface TransferService {
    void transfer(Long fromUserId, TransferRequestDto request);
}
