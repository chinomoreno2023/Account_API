package com.accountapi.dto.email;

import com.accountapi.model.UpdateUserOperationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateEmailRequest {

    @NotBlank(message = "Old email is required")
    @Email
    private String oldEmail;

    @Email
    private String newEmail;

    @NotNull
    private UpdateUserOperationType operationType;
}