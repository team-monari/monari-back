package com.monari.monariback.location.service;

import com.monari.monariback.location.config.LocationApiProperties;
import com.monari.monariback.location.dto.response.LocationResponse;
import com.monari.monariback.location.dto.response.OpenApiLocationResponse;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import com.monari.monariback.location.util.WebClientUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {

    private static final String CATEGORY = "강의실";
    private static final int START = 1;
    private static final int TOTAL_COUNT = 70;

    private final LocationRepository locationRepository;
    private final WebClientUtil webClientUtil;
    private final LocationApiProperties apiProperties;

    /**
     * 공공 API 강의실 소분류에 해당하는 Row 들을 가지고오는 메서드
     *
     * @author Hong
     */
    public void setUp() {
        List<Location> allLocations = getAllLocationsFromApi();
        locationRepository.saveAll(allLocations);
        log.info("총 {}개의 데이터 저장 완료", allLocations.size());
    }

    /**
     * URL 을 생성하여 필요한 컬럼들만을 가져와 Location 에 매핑
     *
     * @return List<Location> Location 객체를 만들어 리스트로 반환
     * @author Hong
     */
    private List<Location> getAllLocationsFromApi() {
        OpenApiLocationResponse responseDto = webClientUtil.get(
            webClientUtil.buildRequestUri(
                apiProperties.getBaseUrl(),
                apiProperties.getKey(),
                apiProperties.getDataType(),
                apiProperties.getServiceName(),
                CATEGORY,
                START,
                TOTAL_COUNT),
            OpenApiLocationResponse.class);

        return responseDto.listPublicReservationInstitution().row().stream()
            .map(dto -> Location.ofCreate(
                dto.svcId(),
                dto.minClassNm(),
                dto.svcStatNm(),
                dto.payAtNm(),
                dto.placeNm(),
                dto.svcUrl(),
                dto.svcOpnBgndt(),
                dto.svcOpnEnddt(),
                dto.rcptBgndt(),
                dto.rcptEnddt(),
                dto.revStdDayNm(),
                dto.revStdDay(),
                dto.x(),
                dto.y()
            ))
            .toList();
    }

    @Transactional(readOnly = true)
    public List<LocationResponse> getLocationList() {
        return locationRepository.findAll()
            .stream()
            .map(LocationResponse::from)
            .toList();
    }

    @Transactional(readOnly = true)
    public LocationResponse getLocation(final Integer locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() ->
            new IllegalArgumentException("해당 공공장소는 존재하지 않습니다"));
        return LocationResponse.from(location);
    }

    public boolean isLocationDataExists() {
        return locationRepository.count() != 0;
    }
}