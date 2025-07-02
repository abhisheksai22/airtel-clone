package com.abhi.airtel.service;

import com.abhi.airtel.entity.Router;
import java.util.List;

public interface RouterService {
    Router saveRouter(Router router);
    Router getRouterById(Long id);
    List<Router> getAllRouters();
    Router updateRouter(Long id, Router router);
    void deleteRouter(Long id);
}

