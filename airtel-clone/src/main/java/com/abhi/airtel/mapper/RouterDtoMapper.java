package com.abhi.airtel.mapper;

import com.abhi.airtel.entity.Band;
import com.abhi.airtel.entity.Router;
import com.abhi.airtel.model.RouterRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Component
@Slf4j
public class RouterDtoMapper implements Function<RouterRequestDto, Router> {

    @Override
    public Router apply(RouterRequestDto routerRequestDto) {
        List<Band> bands = routerRequestDto.getBandRequestDtos() != null ?
                routerRequestDto.getBandRequestDtos().stream()
                        .map(RouterMapper::bandRequestDtoToBand)
                        .toList() : new ArrayList<>();
        log.info("bands :{}", bands);
        Router router = Router.builder()
                .firmwareVersion(routerRequestDto.getFirmwareVersion())
                .status(routerRequestDto.getStatus())
                .model(routerRequestDto.getModel())
                .ipAddress(routerRequestDto.getIpAddress())
                .macAddress(routerRequestDto.getMacAddress())
                .bands(bands)
                .build();

        router.getBands().forEach(band -> band.setRouter(router));
        return router;
    }


}
