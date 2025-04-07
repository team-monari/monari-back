package com.monari.monariback.location.dto;

public record LocationDto(
    String GUBUN,         // 서비스 구분
    String SVCID,         // 서비스 ID
    String MAXCLASSNM,    // 대분류명
    String MINCLASSNM,    // 소분류명
    String SVCSTATNM,     // 서비스 상태명
    String SVCNM,         // 서비스명
    String PAYATNM,       // 요금 구분명 (무료/유료 등)
    String PLACENM,       // 장소명
    String USETGTINFO,    // 이용 대상 정보
    String SVCURL,        // 서비스 URL
    String X,             // X 좌표
    String Y,             // Y 좌표
    String SVCOPNBGNDT,   // 서비스 운영 시작일시
    String SVCOPNENDDT,   // 서비스 운영 종료일시
    String RCPTBGNDT,     // 접수 시작일시
    String RCPTENDDT,     // 접수 종료일시
    String AREANM,        // 지역명
    String IMGURL,        // 이미지 URL
    String DTLCONT,       // 상세내용
    String TELNO,         // 전화번호
    String V_MIN,         // 이용 가능 시작시간
    String V_MAX,         // 이용 가능 종료시간
    String REVSTDDAYNM,   // 예약 기준 요일명
    String REVSTDDAY      // 예약 기준 요일 (숫자)
) {

}
