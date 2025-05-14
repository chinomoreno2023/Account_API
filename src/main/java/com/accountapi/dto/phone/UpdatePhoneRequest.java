package com.accountapi.dto.phone;

import com.accountapi.model.UpdateUserOperationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePhoneRequest {

    @NotBlank(message = "Old phone is required")
    @Pattern(regexp = "^\\d{11}$", message = "Old phone is invalid")
    private String oldPhone;

    @Pattern(regexp = "^\\d{11}$", message = "New phone must be 11 digits")
    private String newPhone;

    @NotNull
    private UpdateUserOperationType operationType;
}
