package com.accountapi.repository.user;

import com.accountapi.model.email.EmailData;
import com.accountapi.model.phone.PhoneData;
import com.accountapi.model.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    public Page<User> searchUsers(String name,
                                  String email,
                                  String phone,
                                  LocalDate dateOfBirth,
                                  Pageable pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        List<User> resultList = createSearchQuery(criteriaBuilder, name, email, phone, dateOfBirth, pageable)
                .getResultList();

        Long total = createCountQuery(criteriaBuilder, name, email, phone, dateOfBirth);

        return new PageImpl<>(resultList, pageable, total);
    }

    private TypedQuery<User> createSearchQuery(CriteriaBuilder criteriaBuilder,
                                               String name,
                                               String email,
                                               String phone,
                                               LocalDate dateOfBirth,
                                               Pageable pageable) {
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> user = query.from(User.class);

        addJoinsAndPredicates(user, query, criteriaBuilder, name, email, phone, dateOfBirth);

        query.select(user).distinct(true);

        TypedQuery<User> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        return typedQuery;
    }

    private Long createCountQuery(CriteriaBuilder criteriaBuilder,
                                  String name,
                                  String email,
                                  String phone,
                                  LocalDate dateOfBirth) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<User> user = countQuery.from(User.class);

        addJoinsAndPredicates(user, countQuery, criteriaBuilder, name, email, phone, dateOfBirth);

        countQuery.select(criteriaBuilder.countDistinct(user));

        return entityManager.createQuery(countQuery).getSingleResult();
    }

    private <T> void addJoinsAndPredicates(Root<User> root,
                                           CriteriaQuery<T> query,
                                           CriteriaBuilder criteriaBuilder,
                                           String name,
                                           String email,
                                           String phone,
                                           LocalDate dateOfBirth) {
        List<Predicate> predicates = new ArrayList<>();

        Join<User, EmailData> emailJoin = root.join("emails", JoinType.LEFT);
        Join<User, PhoneData> phoneJoin = root.join("phones", JoinType.LEFT);

        if (name != null && !name.isBlank()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (email != null && !email.isBlank()) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(emailJoin.get("email")), email.toLowerCase()));
        }
        if (phone != null && !phone.isBlank()) {
            predicates.add(criteriaBuilder.equal(phoneJoin.get("phone"), phone));
        }
        if (dateOfBirth != null) {
            predicates.add(criteriaBuilder.equal(root.get("dateOfBirth"), dateOfBirth));
        }

        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
    }
}