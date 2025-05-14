package com.accountapi.service.strategy.email;

import com.accountapi.dto.email.UpdateEmailRequest;
import com.accountapi.model.UpdateUserOperationType;
import com.accountapi.model.user.User;

public interface EmailUpdateStrategy {
    void execute(User user, UpdateEmailRequest request);
    UpdateUserOperationType getOperationType();
}
