package com.teleport.searchengine.controller;


import com.teleport.searchengine.service.TeleportService;
import com.teleport.searchengine.dto.TeleportResponse;
import com.teleport.searchengine.utils.MappingHelper;
import com.teleport.searchengine.utils.ResponseHelper;
import com.teleport.searchengine.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping(Constants.TELEPORT_BASE_URL)
public class TeleportController {

    private final TeleportService teleportService;

    private final ResponseHelper responseHelper;

    private final MappingHelper mappingHelper;

    @GetMapping("/{country}")
    public ResponseEntity<TeleportResponse> getUrbanArea(@PathVariable String country){
        return new ResponseEntity<>(teleportService.getArea(country), HttpStatus.OK);
    }

    @GetMapping("/listUrbanAreas")
    public ResponseEntity<TeleportResponse> listUrbanAreas(@RequestParam (defaultValue = "0")int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        return new ResponseEntity<>(teleportService.listAreas(), teleportService.listAreas().getHttpStatus());
    }
}
