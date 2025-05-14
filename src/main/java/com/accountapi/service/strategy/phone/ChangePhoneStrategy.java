package com.accountapi.service.strategy.phone;

import com.accountapi.dto.phone.UpdatePhoneRequest;
import com.accountapi.model.phone.PhoneData;
import com.accountapi.model.UpdateUserOperationType;
import com.accountapi.model.user.User;
import com.accountapi.repository.phone.PhoneRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class ChangePhoneStrategy implements PhoneUpdateStrategy {
    private final PhoneRepository phoneRepository;

    @Override
    @Transactional
    public void execute(User user, UpdatePhoneRequest request) {
        PhoneData existing = user.getPhones().stream()
                .filter(phone -> phone.getPhone().equals(request.getOldPhone()))
                .findFirst()
                .orElseThrow(() -> new ValidationException(
                        "User does not have phone: " + request.getOldPhone()));

        if (phoneRepository.existsByPhone(request.getNewPhone())) {
            throw new ValidationException("Phone already exists");
        }

        existing.setPhone(request.getNewPhone());
    }

    @Override
    public UpdateUserOperationType getOperationType() {
        return UpdateUserOperationType.CHANGE;
    }
}
