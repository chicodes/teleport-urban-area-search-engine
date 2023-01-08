package com.teleport.searchengine.service;

import com.teleport.searchengine.dto.TeleportResponse;

public interface TeleportService {

    TeleportResponse getArea(String country);

    TeleportResponse listAreas();

}
