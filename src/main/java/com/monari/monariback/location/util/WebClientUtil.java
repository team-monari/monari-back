package com.monari.monariback.location.util;

import com.monari.monariback.global.config.WebClientConfig;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebClientUtil {

    private final WebClientConfig webClientConfig;

    public <T> T get(final URI uri, final Class<T> responseDtoClass) {
        return webClientConfig.webClient().get()
            .uri(uri)
            .retrieve()
            .onStatus(HttpStatusCode::isError, this::handleError)
            .bodyToMono(responseDtoClass)
            .block();
    }

    public URI buildRequestUri(
        final String baseUrl,
        final String key,
        final String dataType,
        final String serviceName,
        final String category,
        final int start,
        final int end) {
        return UriComponentsBuilder.fromUri(URI.create(baseUrl))
            .pathSegment(key)
            .pathSegment(dataType)
            .pathSegment(serviceName)
            .pathSegment(String.valueOf(start))
            .pathSegment(String.valueOf(end))
            .pathSegment(category)
            .encode(StandardCharsets.UTF_8)
            .build()
            .toUri();
    }

    private Mono<? extends Throwable> handleError(ClientResponse clientResponse) {
        log.error("API 에러 응답: Status code {}", clientResponse.statusCode());
        return clientResponse.bodyToMono(String.class)
            .flatMap(body -> {
                    log.error("API 에러 본문: {}", body);
                    return Mono.error(
                        new RuntimeException("API 요청 실패: " + clientResponse.statusCode()));
                }
            );
    }
}
