package com.abhi.airtel.service.impl;

import com.abhi.airtel.entity.Band;
import com.abhi.airtel.model.PasswordUpdateRequest;
import com.abhi.airtel.model.RouterRequestDto;
import com.abhi.airtel.model.RouterResponseDto;
import com.abhi.airtel.entity.Router;
import com.abhi.airtel.exceptions.RouterNotFoundException;
import com.abhi.airtel.mapper.RouterMapper;
import com.abhi.airtel.repository.RouterRepository;
import com.abhi.airtel.service.RouterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RouterServiceImpl implements RouterService {

    @Autowired
    private RouterRepository routerRepository;

    @Autowired
    private BandServiceImpl bandService;

    @Override
    public RouterResponseDto saveRouter(RouterRequestDto routerRequestDto) {

        Router router = RouterMapper.RouterRequestDtoToRouter(routerRequestDto);
        Router savedRouter = routerRepository.save(router);
        return RouterMapper.RouterToRouterResponseDto(savedRouter);
    }

    @Override
    public RouterResponseDto getRouterById(Long id) {
        Router router = routerRepository.findById(id)
                .orElseThrow(() -> new RouterNotFoundException("Router not found with Id : " + id));


        log.info("Router : {}", router);
        return RouterMapper.RouterToRouterResponseDto(router);
    }

    @Override
    public List<Router> getAllRouters() {
        return routerRepository.findAll();
    }

    @Override
    public Router updateRouter(Long id, Router router) {
        if (routerRepository.existsById(id)) {
            router.setRouterId(id);
            return routerRepository.save(router);
        }
        return null;
    }

    @Override
    public void deleteRouter(Long id) {
        routerRepository.deleteById(id);
    }

    @Override
    public void updatePassword(PasswordUpdateRequest passwordUpdateRequest) {
        Router router = routerRepository.findById(passwordUpdateRequest.getRouterId())
                .orElseThrow(() -> new RouterNotFoundException("Router not found with Id : " + passwordUpdateRequest.getRouterId()));

        Band band = router.getBands().stream()
                .filter(b -> b.getBandId().equals(passwordUpdateRequest.getBandId()))
                .findFirst()
                .orElseThrow(() -> new RouterNotFoundException("Band not found with Id : " + passwordUpdateRequest.getBandId()));

        band.setBandPassword(passwordUpdateRequest.getNewPassword());
        bandService.updateBandPassword(band);
    }
}

