package com.abhi.airtel.service.impl;

import com.abhi.airtel.dto.RouterResponseDto;
import com.abhi.airtel.entity.Router;
import com.abhi.airtel.exceptions.RouterNotFoundException;
import com.abhi.airtel.mapper.RouterMapper;
import com.abhi.airtel.repository.RouterRepository;
import com.abhi.airtel.service.RouterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RouterServiceImpl implements RouterService {
    @Autowired
    private RouterRepository routerRepository;

    @Override
    public Router saveRouter(Router router) {
        return routerRepository.save(router);
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
}

