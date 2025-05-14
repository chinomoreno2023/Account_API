package com.accountapi.service.user;

import com.accountapi.dto.user.CreateUserRequest;
import com.accountapi.dto.email.UpdateEmailRequest;
import com.accountapi.dto.phone.UpdatePhoneRequest;
import com.accountapi.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface UserService {
    Page<User> searchUsers(String name,
                           String email,
                           String phone,
                           LocalDate dateOfBirth,
                           Pageable pageable);

    User createUser(CreateUserRequest request);
    User updateUser(Long id, UpdateEmailRequest request);
    User updateUser(Long id, UpdatePhoneRequest request);
    List<User> searchUsersCached(String name,
                                 String email,
                                 String phone,
                                 LocalDate dateOfBirth,
                                 Pageable pageable);
}
