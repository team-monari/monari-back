package com.monari.monariback.batch.itemwriter;

import com.monari.monariback.location.dto.OpenApiLocationDto;
import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class PublicApiDataWriter implements ItemWriter<OpenApiLocationDto> {

    private final LocationRepository locationRepository;

    /**
     * Spring Batch에서 한 번에 쓰여질 데이터(Chunk 단위)를 받아, Entity로 변환 후 Repository를 통해 저장.
     *
     * @param items OpenApiLocationDto 목록
     * @author Hong
     */
    @Override
    public void write(Chunk<? extends OpenApiLocationDto> items) {
        List<Location> entities = items.getItems()
            .stream()
            .map(data -> Location.ofCreate(
                    data.svcId(),
                    data.minClassNm(),
                    data.svcStatNm(),
                    data.payAtNm(),
                    data.placeNm(),
                    data.svcUrl(),
                    data.svcOpnBgndt(),
                    data.svcOpnEnddt(),
                    data.rcptBgndt(),
                    data.rcptEnddt(),
                    data.revStdDayNm(),
                    data.revStdDay(),
                    data.x(),
                    data.y()
                )
            )
            .collect(Collectors.toList());

        locationRepository.saveAll(entities);
    }
}