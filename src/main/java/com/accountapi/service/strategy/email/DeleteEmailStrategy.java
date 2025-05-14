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
import java.util.List;

@RequiredArgsConstructor
@Component
public class DeleteEmailStrategy implements EmailUpdateStrategy {

    @Override
    @Transactional
    public void execute(User user, UpdateEmailRequest request) {
        List<EmailData> emails = user.getEmails();
        if (emails.size() <= 1) {
            throw new ValidationException("User must have at least one email");
        }

        EmailData emailToRemove = emails.stream()
                .filter(email -> email.getEmail().equals(request.getOldEmail()))
                .findFirst()
                .orElseThrow(() -> new ValidationException("User does not have email: " + request.getOldEmail()));

        emails.remove(emailToRemove);
    }

    @Override
    public UpdateUserOperationType getOperationType() {
        return UpdateUserOperationType.DELETE;
    }
}
