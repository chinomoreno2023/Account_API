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

@Component
@RequiredArgsConstructor
public class AddEmailStrategy implements EmailUpdateStrategy {
    private final EmailRepository emailDataRepository;

    @Override
    @Transactional
    public void execute(User user, UpdateEmailRequest request) {
        String newEmail = request.getNewEmail();

        if (emailDataRepository.existsByEmail(newEmail)) {
            throw new ValidationException("Email already exists");
        }

        EmailData emailData = new EmailData();
        emailData.setEmail(newEmail);
        emailData.setUser(user);
        user.getEmails().add(emailData);
    }

    @Override
    public UpdateUserOperationType getOperationType() {
        return UpdateUserOperationType.ADD;
    }
}
