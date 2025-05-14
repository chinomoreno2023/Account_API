package com.accountapi.model.phone;

import com.accountapi.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "phone_data", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"phone"})
})
@Setter
@Getter
public class PhoneData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 13, nullable = false, unique = true)
    @Pattern(regexp = "^\\d{11}$")
    private String phone;
}
