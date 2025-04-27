package com.monari.monariback.batch.itemwriter;

import com.monari.monariback.location.entity.Location;
import com.monari.monariback.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional
public class PublicApiDataWriter implements ItemWriter<Location> {

    private final LocationRepository locationRepository;

    /**
     * Spring Batch에서 한 번에 쓰여질 데이터(Chunk 단위)를 받아, Entity로 변환 후 Repository를 통해 저장.
     *
     * @param locations Location 목록
     * @author Hong
     */
    @Override
    public void write(Chunk<? extends Location> locations) {
        locationRepository.saveAll(locations.getItems());
    }
}