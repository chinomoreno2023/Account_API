package com.accountapi.service.strategy.phone;

import com.accountapi.dto.phone.UpdatePhoneRequest;
import com.accountapi.model.UpdateUserOperationType;
import com.accountapi.model.user.User;

public interface PhoneUpdateStrategy {
    void execute(User user, UpdatePhoneRequest request);
    UpdateUserOperationType getOperationType();
}
