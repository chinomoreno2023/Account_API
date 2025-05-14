package com.accountapi.service.processor.phone;

import com.accountapi.dto.phone.UpdatePhoneRequest;
import com.accountapi.model.user.User;

public interface PhoneUpdateProcessor {
    void process(User user, UpdatePhoneRequest request);
}
