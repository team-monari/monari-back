package com.monari.monariback.batch.itemReader;

import com.monari.monariback.location.config.LocationApiProperties;
import com.monari.monariback.location.dto.OpenApiLocationDto;
import com.monari.monariback.location.dto.response.OpenApiLocationResponse;
import com.monari.monariback.location.util.WebClientUtil;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PublicApiDataReader implements ItemReader<OpenApiLocationDto> {

    private static final String CATEGORY = "강의실";
    private static final int START = 1;
    private static final int TOTAL_COUNT = 100;
    private final LocationApiProperties apiProperties;
    private final WebClientUtil webClientUtil;
    private Iterator<OpenApiLocationDto> iterator;

    /**
     * 외부 공공 API에서 데이터를 조회하고, DTO 리스트로 반환하는 내부 메서드
     *
     * @return OpenApiLocationDto 리스트
     * @author Hong
     */
    private List<OpenApiLocationDto> fetchDataFromApi() {
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

        return responseDto.listPublicReservationInstitution().row().stream().toList();
    }

    /**
     * Spring Batch가 데이터를 읽을 때 호출되는 메서드. 처음 한 번 API로부터 전체 데이터를 가져오고, 이후 한 개씩 반환.
     *
     * @return 다음 OpenApiLocationDto 객체 또는 더 이상 없으면 null
     * @author Hong
     */
    @Override
    public OpenApiLocationDto read() {
        if (iterator == null) {
            List<OpenApiLocationDto> data = fetchDataFromApi();
            iterator = data.iterator();
        }
        return iterator.hasNext() ? iterator.next() : null;
    }
}
