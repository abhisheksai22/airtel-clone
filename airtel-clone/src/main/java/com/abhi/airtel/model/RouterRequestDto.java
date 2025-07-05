package com.abhi.airtel.model;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RouterRequestDto {

    @NotNull(message = "{router.model.notnull}")
    private String model;
    @NotNull(message = "{router.macAddress.notnull}")
    private String macAddress;
    private String ipAddress;
    private String firmwareVersion;
    @NotNull(message = "{router.status.notnull}")
    private String status;

    @Valid
    private List<BandRequestDto> bandRequestDtos;


}
