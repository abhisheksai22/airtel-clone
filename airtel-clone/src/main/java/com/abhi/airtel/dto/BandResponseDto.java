package com.abhi.airtel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BandResponseDto {
    private Long bandId;
    private String bandName;
    private String bandType;
}
