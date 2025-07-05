package com.abhi.airtel.mapper;

import com.abhi.airtel.dto.BandResponseDto;
import com.abhi.airtel.dto.RouterResponseDto;
import com.abhi.airtel.entity.Band;
import com.abhi.airtel.entity.Router;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class RouterMapper {

    public static RouterResponseDto RouterToRouterResponseDto(Router router) {
        List<BandResponseDto> bandDtos = router.getBands().stream()
                .map(RouterMapper::bandToBandDto)
                .toList();
        return RouterResponseDto.builder()
                .routerId(router.getRouterId())
                .model(router.getModel())
                .status(router.getStatus())
                .ipAddress(router.getIpAddress())
                .macAddress(router.getMacAddress())
                .bands(bandDtos)
                .build();
    }

    public static BandResponseDto bandToBandDto(Band band) {
        return BandResponseDto.builder()
                .bandId(band.getBandId())
                .bandName(band.getBandName())
                .bandType(String.valueOf(band.getBandType()))
                .build();
    }

}
