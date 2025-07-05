package com.abhi.airtel.model;

import com.abhi.airtel.entity.Device;
import com.abhi.airtel.validation.ValidBandType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BandRequestDto {

    @NotNull(message = "{band.name.notnull}")
    private String bandName;

    @NotNull(message = "{band.password.notnull}")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "{band.password.pattern}"
    )
    private String bandPassword;

    @NotNull(message = "{band.type.notnull}")
    @ValidBandType
    private String bandType;

    private List<Device> devices;
}