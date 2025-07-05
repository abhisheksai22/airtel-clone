package com.abhi.airtel.service;

import com.abhi.airtel.model.PasswordUpdateRequest;
import com.abhi.airtel.model.RouterRequestDto;
import com.abhi.airtel.model.RouterResponseDto;
import com.abhi.airtel.entity.Router;
import jakarta.validation.Valid;

import java.util.List;

public interface RouterService {
    RouterResponseDto saveRouter(RouterRequestDto routerRequestDto);
    RouterResponseDto getRouterById(Long id);
    List<Router> getAllRouters();
    Router updateRouter(Long id, Router router);
    void deleteRouter(Long id);

    void updatePassword(PasswordUpdateRequest passwordUpdateRequest);
}

