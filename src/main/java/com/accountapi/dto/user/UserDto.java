package com.accountapi.dto.user;

import com.accountapi.model.email.EmailData;
import com.accountapi.model.phone.PhoneData;
import com.accountapi.model.user.User;
import java.time.LocalDate;
import java.util.List;

public record UserDto(
        Long id,
        String name,
        LocalDate dateOfBirth,
        List<String> emails,
        List<String> phones
) {
    public static UserDto fromEntity(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getDateOfBirth(),
                user.getEmails().stream().map(EmailData::getEmail).toList(),
                user.getPhones().stream().map(PhoneData::getPhone).toList()
        );
    }
}
