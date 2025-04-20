package com.monari.monariback.location.init;

import com.monari.monariback.location.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LocationDataInitializer {

    private final LocationService locationService;
    @Value("${location.init:false}")
    private boolean initLoad;

    @Bean
    public ApplicationRunner loadLocationData() {
        return args -> {
//            if (!locationService.isLocationDataExists()) {
            log.info("서버 시작 시 Location 데이터 설정 시작");
            locationService.setUp();
            log.info("Location 데이터 설정 완료");
//            }
        };
    }
}
