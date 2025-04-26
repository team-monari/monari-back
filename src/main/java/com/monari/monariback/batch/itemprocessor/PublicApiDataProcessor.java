package com.monari.monariback.batch.itemprocessor;

import com.monari.monariback.location.dto.OpenApiLocationDto;
import com.monari.monariback.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublicApiDataProcessor implements
    ItemProcessor<OpenApiLocationDto, OpenApiLocationDto> {

    private final LocationRepository locationRepository;

    /**
     * 외부 API에서 가져온 OpenApiLocationDto가 DB에 이미 존재하는지 확인하고, 존재하지 않는 경우에만 해당 데이터를 다음 단계로 넘긴다.
     *
     * @param item OpenApiLocationDto 객체 (API에서 읽어온 하나의 데이터)
     * @return 기존에 존재하면 null, 존재하지 않으면 해당 item 반환
     * @author Hong
     */
    @Override
    public OpenApiLocationDto process(OpenApiLocationDto item) {
        boolean exists = locationRepository.existsByServiceId(item.svcId());
        return exists ? null : item;
    }
}
