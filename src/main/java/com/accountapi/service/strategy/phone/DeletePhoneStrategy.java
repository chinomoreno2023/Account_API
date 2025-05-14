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
import java.util.List;

@RequiredArgsConstructor
@Component
public class DeletePhoneStrategy implements PhoneUpdateStrategy{

    @Override
    @Transactional
    public void execute(User user, UpdatePhoneRequest request) {
        List<PhoneData> phones = user.getPhones();
        if (phones.size() <= 1) {
            throw new ValidationException("User must have at least one phone");
        }

        PhoneData phoneToRemove = phones.stream()
                .filter(phone -> phone.getPhone().equals(request.getOldPhone()))
                .findFirst()
                .orElseThrow(() -> new ValidationException("User does not have phone: " + request.getOldPhone()));

        phones.remove(phoneToRemove);
    }

    @Override
    public UpdateUserOperationType getOperationType() {
        return UpdateUserOperationType.DELETE;
    }
}
