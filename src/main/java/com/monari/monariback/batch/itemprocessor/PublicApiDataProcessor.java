package com.monari.monariback.batch.itemprocessor;

import com.monari.monariback.location.dto.OpenApiLocationDto;
import com.monari.monariback.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PublicApiDataProcessor implements
    ItemProcessor<OpenApiLocationDto, OpenApiLocationDto> {

    private final LocationRepository locationRepository;

    @Override
    public OpenApiLocationDto process(OpenApiLocationDto item) {
        boolean exists = locationRepository.existsByServiceId(item.svcId());
        return exists ? null : item;
    }
}
