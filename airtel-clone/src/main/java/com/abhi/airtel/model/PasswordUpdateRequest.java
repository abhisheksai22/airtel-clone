package com.abhi.airtel.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordUpdateRequest {

    @NotNull
    private String oldPassword;
    @NotNull
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "{band.password.pattern}"
    )
    private String newPassword;
    @NotNull
    private Long routerId;
    @NotNull
    private Long bandId;
    // Todo
//    @NotNull
//    private Long userId;

}
