package com.accountapi.repository.email;

import com.accountapi.model.email.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailData, Long> {
    boolean existsByEmail(String email);
    EmailData save(EmailData emailData);
    void delete(EmailData entity);
}
