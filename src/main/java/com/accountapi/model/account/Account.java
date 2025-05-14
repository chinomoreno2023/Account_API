package com.accountapi.model.account;

import com.accountapi.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal balance;

    @Column(nullable = false)
    @DecimalMin(value = "0.00", inclusive = true)
    private BigDecimal initialBalance;
}
