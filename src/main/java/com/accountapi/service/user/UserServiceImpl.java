package com.accountapi.service.user;

import com.accountapi.dto.user.CreateUserRequest;
import com.accountapi.dto.email.UpdateEmailRequest;
import com.accountapi.dto.phone.UpdatePhoneRequest;
import com.accountapi.model.account.Account;
import com.accountapi.model.email.EmailData;
import com.accountapi.model.phone.PhoneData;
import com.accountapi.model.user.User;
import com.accountapi.repository.user.UserRepository;
import com.accountapi.service.processor.email.EmailUpdateProcessor;
import com.accountapi.service.processor.phone.PhoneUpdateProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EmailUpdateProcessor emailUpdateProcessor;
    private final PhoneUpdateProcessor phoneUpdateProcessor;

    @Override
    @Cacheable(value = "userSearch",
            key = "{#name, #email, #phone, #dateOfBirth, #pageable.pageNumber, #pageable.pageSize}")
    public List<User> searchUsersCached(String name,
                                        String email,
                                        String phone,
                                        LocalDate dateOfBirth,
                                        Pageable pageable) {
        return userRepository.searchUsers(name, email, phone, dateOfBirth, pageable).getContent();
    }

    @Override
    public Page<User> searchUsers(String name,
                                  String email,
                                  String phone,
                                  LocalDate dateOfBirth,
                                  Pageable pageable) {
        List<User> users = searchUsersCached(name, email, phone, dateOfBirth, pageable);
        return new PageImpl<>(users, pageable, users.size());
    }

    @Transactional
    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        EmailData email = new EmailData();
        email.setEmail(request.getEmail());
        email.setUser(user);

        PhoneData phone = new PhoneData();
        phone.setPhone(request.getPhone());
        phone.setUser(user);

        user.setEmails(List.of(email));
        user.setPhones(List.of(phone));

        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(1000));
        account.setInitialBalance(BigDecimal.valueOf(1000));
        account.setUser(user);
        user.setAccount(account);

        return userRepository.save(user);
    }

    @CacheEvict(value = "userById", key = "#id")
    @Transactional
    public User updateUser(Long id, UpdateEmailRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        emailUpdateProcessor.process(user, request);

        return userRepository.save(user);
    }

    @Transactional
    @CacheEvict(value = "userById", key = "#id")
    public User updateUser(Long id, UpdatePhoneRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        phoneUpdateProcessor.process(user, request);

        return userRepository.save(user);
    }
}
