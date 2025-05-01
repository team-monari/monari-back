package com.monari.monariback.location.controller;

import com.monari.monariback.location.dto.response.LocationResponse;
import com.monari.monariback.location.service.LocationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping()
    public ResponseEntity<List<LocationResponse>> getLocationList() {
        return ResponseEntity.ok(locationService.getLocationList());
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationResponse> getLocation(
        @PathVariable(name = "locationId") final Integer locationId) {
        return ResponseEntity.ok(locationService.getLocation(locationId));
    }


}
