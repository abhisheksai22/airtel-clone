package com.abhi.airtel.service.impl;

import com.abhi.airtel.entity.Router;
import com.abhi.airtel.repository.RouterRepository;
import com.abhi.airtel.service.RouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouterServiceImpl implements RouterService {
    @Autowired
    private RouterRepository routerRepository;

    @Override
    public Router saveRouter(Router router) {
        return routerRepository.save(router);
    }

    @Override
    public Router getRouterById(Long id) {
        return routerRepository.findById(id).orElse(null);
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

