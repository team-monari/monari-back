package com.monari.monariback.batch.itemprocessor;

import com.monari.monariback.location.dto.OpenApiLocationDto;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class PublicApiDataProcessor implements
    ItemProcessor<OpenApiLocationDto, Location> {

    private final LocationRepository locationRepository;

    /**
     * 외부 API에서 가져온 OpenApiLocationDto가 DB에 이미 존재하는지 확인하고, 존재하지 않는 경우에만 해당 데이터를 다음 단계로 넘긴다.
     *
     * @param item OpenApiLocationDto 객체 (API에서 읽어온 하나의 데이터)
     * @return 기존에 존재하면 해당 Location 업데이트 후 반환, 존재하지 않으면 새 Location 반환
     * @author Hong
     */
    @Override
    public Location process(OpenApiLocationDto item) {
        Optional<Location> existingLocation = locationRepository.findByServiceId(item.svcId());

        if (existingLocation.isPresent()) {
            Location location = existingLocation.get();
            if (location.updateIfChanged(
                item.minClassNm(),
                item.svcStatNm(),
                item.payAtNm(),
                item.placeNm(),
                item.svcUrl(),
                item.svcOpnBgndt(),
                item.svcOpnEnddt(),
                item.rcptBgndt(),
                item.rcptEnddt(),
                item.revStdDayNm(),
                item.revStdDay(),
                item.x(),
                item.y())
            ) {
                return location;
            }
        } else {
            return Location.ofCreate(
                item.svcId(),
                item.minClassNm(),
                item.svcStatNm(),
                item.payAtNm(),
                item.placeNm(),
                item.svcUrl(),
                item.svcOpnBgndt(),
                item.svcOpnEnddt(),
                item.rcptBgndt(),
                item.rcptEnddt(),
                item.revStdDayNm(),
                item.revStdDay(),
                item.x(),
                item.y()
            );
        }

        return null; // 변경되지 않았거나 처리할 데이터가 없다면 null 반환
    }
}