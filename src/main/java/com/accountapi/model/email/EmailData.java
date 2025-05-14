package com.accountapi.model.email;

import com.accountapi.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "email_data", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
@Setter
@Getter
public class EmailData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 200, nullable = false, unique = true)
    @Email
    private String email;
}
