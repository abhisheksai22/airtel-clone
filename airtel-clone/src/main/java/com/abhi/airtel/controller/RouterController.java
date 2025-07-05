package com.abhi.airtel.controller;

import com.abhi.airtel.model.PasswordUpdateRequest;
import com.abhi.airtel.model.RouterRequestDto;
import com.abhi.airtel.model.RouterResponseDto;
import com.abhi.airtel.entity.Router;
import com.abhi.airtel.service.RouterService;
import jakarta.validation.Valid;
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
    public ResponseEntity<RouterResponseDto> createRouter(@RequestBody @Valid RouterRequestDto routerRequestDto) {
        return new ResponseEntity<>(routerService.saveRouter(routerRequestDto), HttpStatus.CREATED);
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


    @PatchMapping
    public ResponseEntity<?> updatePassword(@RequestBody @Valid PasswordUpdateRequest passwordUpdateRequest) {
        log.info("updating password...");
        if (passwordUpdateRequest.getOldPassword().equals(passwordUpdateRequest.getNewPassword())) {
            return ResponseEntity.badRequest().body("New password cannot be the same as the old password");
        }

        routerService.updatePassword(passwordUpdateRequest);

        return ResponseEntity.ok("Password updated successfully");
    }
}

