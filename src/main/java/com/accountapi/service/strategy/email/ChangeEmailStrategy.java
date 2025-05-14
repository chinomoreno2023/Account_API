package com.accountapi.service.strategy.email;

import com.accountapi.dto.email.UpdateEmailRequest;
import com.accountapi.model.email.EmailData;
import com.accountapi.model.UpdateUserOperationType;
import com.accountapi.model.user.User;
import com.accountapi.repository.email.EmailRepository;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class ChangeEmailStrategy implements EmailUpdateStrategy {
    private final EmailRepository emailRepository;

    @Override
    @Transactional
    public void execute(User user, UpdateEmailRequest request) {
        EmailData existing = user.getEmails().stream()
                .filter(email -> email.getEmail().equals(request.getOldEmail()))
                .findFirst()
                .orElseThrow(() -> new ValidationException(
                        "User doesn't have email: " + request.getOldEmail()));

        if (emailRepository.existsByEmail(request.getNewEmail())) {
            throw new ValidationException("Email already exists: " + request.getNewEmail());
        }

        existing.setEmail(request.getNewEmail());
    }

    @Override
    public UpdateUserOperationType getOperationType() {
        return UpdateUserOperationType.CHANGE;
    }
}
