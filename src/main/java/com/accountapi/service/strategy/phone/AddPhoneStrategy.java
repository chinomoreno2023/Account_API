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

@Component
@RequiredArgsConstructor
public class AddPhoneStrategy implements PhoneUpdateStrategy {
    private final PhoneRepository phoneRepository;

    @Override
    @Transactional
    public void execute(User user, UpdatePhoneRequest request) {
        String newPhone = request.getNewPhone();

        if (phoneRepository.existsByPhone(newPhone)) {
            throw new ValidationException("Phone already exists");
        }

        PhoneData phoneData = new PhoneData();
        phoneData.setPhone(newPhone);
        phoneData.setUser(user);
        user.getPhones().add(phoneData);
    }

    @Override
    public UpdateUserOperationType getOperationType() {
        return UpdateUserOperationType.ADD;
    }
}
