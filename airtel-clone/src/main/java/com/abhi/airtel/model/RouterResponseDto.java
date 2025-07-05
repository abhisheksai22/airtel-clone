package com.abhi.airtel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouterResponseDto {

    private Long routerId;
    private String model;
    private String macAddress;
    private String ipAddress;
    private String firmwareVersion;
    private String status;
    private List<BandResponseDto> bands;
}
