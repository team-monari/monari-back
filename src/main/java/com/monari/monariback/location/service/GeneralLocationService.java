package com.monari.monariback.location.service;


import com.monari.monariback.common.error.ErrorCode;
import com.monari.monariback.common.exception.NotFoundException;
import com.monari.monariback.location.dto.response.GeneralLocationResponse;
import com.monari.monariback.location.entity.GeneralLocation;
import com.monari.monariback.location.repository.GeneralLocationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GeneralLocationService {

    private final GeneralLocationRepository generalLocationRepository;

    @Transactional(readOnly = true)
    public GeneralLocationResponse getGeneralLocation(final Integer locationId) {
        final GeneralLocation location = generalLocationRepository.findById(locationId)
            .orElseThrow(() -> new NotFoundException(ErrorCode.LOCATION_NOT_FOUND)
            );
        return GeneralLocationResponse.from(location);
    }

    @Transactional(readOnly = true)
    public List<GeneralLocationResponse> getLocationList() {
        return generalLocationRepository.findAll()
            .stream()
            .map(GeneralLocationResponse::from)
            .toList();
    }
}
