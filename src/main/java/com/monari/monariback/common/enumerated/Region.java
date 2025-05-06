package com.monari.monariback.common.enumerated;

import com.monari.monariback.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static com.monari.monariback.common.error.ErrorCode.REGION_NOT_FOUND;

@Getter
@AllArgsConstructor
public enum Region {
    GANGNAM_GU("강남구"),
    GANGDONG_GU("강동구"),
    GANGBUK_GU("강북구"),
    GANGSEO_GU("강서구"),
    GWANAK_GU("관악구"),
    GWANGJIN_GU("광진구"),
    GURO_GU("구로구"),
    GEUMCHEON_GU("금천구"),
    NOWON_GU("노원구"),
    DOBONG_GU("도봉구"),
    DONGDAEMUN_GU("동대문구"),
    DONGJAK_GU("동작구"),
    MAPO_GU("마포구"),
    SEODAEMUN_GU("서대문구"),
    SEOCHO_GU("서초구"),
    SEONGDONG_GU("성동구"),
    SEONGBUK_GU("성북구"),
    SONGPA_GU("송파구"),
    YANGCHEON_GU("양천구"),
    YEONGDEUNGPO_GU("영등포구"),
    YONGSAN_GU("용산구"),
    EUNPYEONG_GU("은평구"),
    JONGNO_GU("종로구"),
    JUNG_GU("중구"),
    JUNGRANG_GU("중랑구"),
    ONLINE("온라인");

    private final String koreanName;

    public static Region from(String koreanName) {
        return Arrays.stream(values())
                .filter(val -> koreanName.equals(val.koreanName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(REGION_NOT_FOUND));
    }

}