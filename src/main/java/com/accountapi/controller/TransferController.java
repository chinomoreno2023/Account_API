package com.accountapi.controller;

import com.accountapi.dto.transfer.TransferRequestDto;
import com.accountapi.service.account.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {
    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<String> transfer(@RequestBody @Valid TransferRequestDto request,
                              Authentication authentication) {
        Long fromUserId = (Long) authentication.getPrincipal();
        transferService.transfer(fromUserId, request);
        return ResponseEntity.ok(
                "Transfer from user " + fromUserId + " to user " + request.getTo() + " was successful");
    }
}
