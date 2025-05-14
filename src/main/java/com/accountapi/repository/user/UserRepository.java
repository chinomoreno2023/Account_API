package com.accountapi.repository.user;

import com.accountapi.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findById(Long id);
    Optional<User> findByEmailsEmail(String email);
    User save(User user);
}
