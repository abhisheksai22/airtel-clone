package com.abhi.airtel.controller;

import com.abhi.airtel.dto.RouterResponseDto;
import com.abhi.airtel.entity.Router;
import com.abhi.airtel.service.RouterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        log.info("Routers ...");
        return routerService.saveRouter(router);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouterResponseDto> getRouterById(@PathVariable Long id) {
        return new ResponseEntity<>(routerService.getRouterById(id), HttpStatus.OK);
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

