package com.monari.monariback.location.controller;


import com.monari.monariback.location.dto.response.GeneralLocationResponse;
import com.monari.monariback.location.service.GeneralLocationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/general_locations")
@RequiredArgsConstructor
public class GeneralLocationController {


    private final GeneralLocationService generalLocationService;

    @GetMapping("/{locationId}")
    public ResponseEntity<GeneralLocationResponse> getLocation(

        @PathVariable(name = "locationId") final Integer locationId) {
        return ResponseEntity.ok(generalLocationService.getGeneralLocation(locationId));

    }

    @GetMapping()
    public ResponseEntity<List<GeneralLocationResponse>> getLocationList() {
        return ResponseEntity.ok(generalLocationService.getLocationList());
    }

}
