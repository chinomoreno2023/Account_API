package com.accountapi.service.account;

import com.accountapi.repository.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceUpdateService {
    private final AccountRepository accountRepository;

    @Transactional
    @Scheduled(fixedRate = 30_000)
    public void increaseBalances() {
        accountRepository.findAll().forEach(account -> {
            BigDecimal maxAllowed = account.getInitialBalance().multiply(BigDecimal.valueOf(2.07));
            BigDecimal newBalance = account.getBalance().multiply(BigDecimal.valueOf(1.1));
            account.setBalance(newBalance.min(maxAllowed));
        });
    }
}
