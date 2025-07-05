package com.abhi.airtel.service;

import com.abhi.airtel.dto.RouterResponseDto;
import com.abhi.airtel.entity.Router;
import java.util.List;

public interface RouterService {
    Router saveRouter(Router router);
    RouterResponseDto getRouterById(Long id);
    List<Router> getAllRouters();
    Router updateRouter(Long id, Router router);
    void deleteRouter(Long id);
}

