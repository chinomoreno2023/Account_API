package com.accountapi.service.account;

import com.accountapi.dto.transfer.TransferRequestDto;
import com.accountapi.model.event.Event;
import com.accountapi.model.user.User;
import com.accountapi.repository.user.UserRepository;
import com.accountapi.service.producer.ProducerService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private final ProducerService producerService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void transfer(Long fromUserId, TransferRequestDto request) {
        if (fromUserId.equals(request.getTo())) {
            throw new ValidationException("You can't transfer money to yourself");
        }

        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new EntityNotFoundException("Sender not found"));

        User toUser = userRepository.findById(request.getTo())
                .orElseThrow(() -> new EntityNotFoundException("Recipient not found"));

        Long from = fromUser.getAccount().getId();
        Long to = toUser.getAccount().getId();

        if (fromUser.getAccount().getBalance().compareTo(request.getValue()) < 0) {
            throw new ValidationException("Insufficient funds");
        }

        producerService.createEvent(
                new Event(UUID.randomUUID().toString(), from, to, request.getValue()));
    }
}
