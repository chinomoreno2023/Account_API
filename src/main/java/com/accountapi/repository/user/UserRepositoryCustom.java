package com.accountapi.repository.user;

import com.accountapi.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

public interface UserRepositoryCustom {
    Page<User> searchUsers(String name,
                           String email,
                           String phone,
                           LocalDate dateOfBirth,
                           Pageable pageable);

}
