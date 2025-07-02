package com.abhi.airtel.controller;

import com.abhi.airtel.entity.Router;
import com.abhi.airtel.service.RouterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routers")
@Slf4j
public class RouterController {

    @Autowired
    private RouterService routerService;

    @PostMapping
    public Router createRouter(@RequestBody Router router) {
        Router router1 = routerService.saveRouter(router);
        log.info("Router created with ID: {}, band : {}, devices: {}", router1, router1.getBands(), router1.getBands().get(0).getDevices());
        return router1;
    }

    @GetMapping("/{id}")
    public Router getRouterById(@PathVariable Long id) {
        return routerService.getRouterById(id);
    }

    @GetMapping
    public List<Router> getAllRouters() {
        return routerService.getAllRouters();
    }

    @PutMapping("/{id}")
    public Router updateRouter(@PathVariable Long id, @RequestBody Router router) {
        return routerService.updateRouter(id, router);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRouter(@PathVariable Long id) {
        routerService.deleteRouter(id);
        return ResponseEntity.noContent().build();
    }
}

