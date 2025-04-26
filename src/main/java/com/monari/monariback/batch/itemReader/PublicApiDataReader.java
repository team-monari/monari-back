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

    @Override
    public OpenApiLocationDto read() {
        if (iterator == null) {
            List<OpenApiLocationDto> data = fetchDataFromApi();
            iterator = data.iterator();
        }
        return iterator.hasNext() ? iterator.next() : null;
    }
}
