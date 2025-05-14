package com.accountapi.repository.phone;

import com.accountapi.model.phone.PhoneData;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneData, Long> {
    boolean existsByPhone(String phone);
    PhoneData save(PhoneData phoneData);
    void delete(PhoneData entity);
}
