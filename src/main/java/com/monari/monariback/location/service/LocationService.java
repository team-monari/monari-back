package com.monari.monariback.location.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monari.monariback.location.config.LocationApiProperties;
import com.monari.monariback.location.dto.LocationDto;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import com.monari.monariback.location.util.WebClientUtil;
import java.util.ArrayList;
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
    private final ObjectMapper objectMapper;
    private final WebClientUtil webClientUtil;
    private final LocationApiProperties apiProperties;

    /**
     * 공공 API 강의실 소분류에 해당하는 Row 들을 가지고오는 메서드
     *
     * @author Hong
     */
    public void setUp() {
        if (locationRepository.count() == 0) {
            List<Location> allLocations = getAllLocationsFromApi();
            locationRepository.saveAll(allLocations);
            log.info("총 {}개의 데이터 저장 완료", allLocations.size());
        }
    }

    /**
     * URL 을 생성하여 필요한 컬럼들만을 가져와 Location 에 매핑
     *
     * @return List<Location> Location 객체를 만들어 리스트로 반환
     * @author Hong
     */
    private List<Location> getAllLocationsFromApi() {
        List<Location> result = new ArrayList<>(TOTAL_COUNT);
        String responseBody = webClientUtil.get(
            webClientUtil.buildRequestUri(
                apiProperties.getBaseUrl(),
                apiProperties.getKey(),
                apiProperties.getDataType(),
                apiProperties.getServiceName(),
                CATEGORY,
                START,
                TOTAL_COUNT), String.class);
        try {
            result.addAll(parseLocationList(responseBody));
        } catch (JsonProcessingException e) {
            log.warn("Json 파싱 중 오류 발생");
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * String 을 Dto 로 매핑후 Entity 로 변환하는 메서드
     *
     * @param responseBody 공공 API responseBody
     * @return List<Location> Location 객체를 만들어 리스트로 반환
     * @author Hong
     */

    private List<Location> parseLocationList(final String responseBody)
        throws JsonProcessingException {
        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode rows = root.path(apiProperties.getServiceName()).path("row");
        List<Location> locationList = new ArrayList<>(TOTAL_COUNT);
        if (rows.isArray()) {
            for (JsonNode node : rows) {
                LocationDto dto = objectMapper.treeToValue(node, LocationDto.class);
                locationList.add(Location.ofCreate(
                    dto.MINCLASSNM(),
                    dto.SVCSTATNM(),
                    dto.PAYATNM(),
                    dto.PLACENM(),
                    dto.SVCURL(),
                    dto.SVCOPNBGNDT(),
                    dto.SVCOPNENDDT(),
                    dto.RCPTBGNDT(),
                    dto.RCPTENDDT(),
                    dto.REVSTDDAYNM(),
                    dto.REVSTDDAY())
                );
            }
        }
        return locationList;
    }
}