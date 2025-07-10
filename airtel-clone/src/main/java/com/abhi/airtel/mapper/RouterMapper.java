package com.abhi.airtel.mapper;

import com.abhi.airtel.model.BandRequestDto;
import com.abhi.airtel.model.BandResponseDto;
import com.abhi.airtel.model.RouterRequestDto;
import com.abhi.airtel.model.RouterResponseDto;
import com.abhi.airtel.entity.Band;
import com.abhi.airtel.entity.BandType;
import com.abhi.airtel.entity.Router;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
@Slf4j
public class RouterMapper {

//    public static Router RouterRequestDtoToRouter(RouterRequestDto routerRequestDto) {
//        List<Band> bands = routerRequestDto.getBandRequestDtos() != null ?
//                routerRequestDto.getBandRequestDtos().stream()
//                        .map(RouterMapper::bandRequestDtoToBand)
//                        .toList() : new ArrayList<>();
//        log.info("bands :{}", bands);
//        Router router = Router.builder()
//                .firmwareVersion(routerRequestDto.getFirmwareVersion())
//                .status(routerRequestDto.getStatus())
//                .model(routerRequestDto.getModel())
//                .ipAddress(routerRequestDto.getIpAddress())
//                .macAddress(routerRequestDto.getMacAddress())
//                .bands(bands)
//                .build();
//
//        router.getBands().forEach(band -> band.setRouter(router));
//        return router;
//    }

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

    public static Band bandRequestDtoToBand(BandRequestDto bandRequestDto) {
        return Band.builder()
            .bandName(bandRequestDto.getBandName())
            .bandPassword(bandRequestDto.getBandPassword())
            .bandType(BandType.valueOf(bandRequestDto.getBandType())) 
            .devices(bandRequestDto.getDevices())
            .build();
    }

}
