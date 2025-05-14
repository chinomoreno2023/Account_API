package com.accountapi.service.processor.email;

import com.accountapi.dto.email.UpdateEmailRequest;
import com.accountapi.model.user.User;

public interface EmailUpdateProcessor {
    void process(User user, UpdateEmailRequest request);
}
