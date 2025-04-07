package com.monari.monariback.location.dto;

public record LocationDto(
    String gubun,          // 서비스 구분
    String svcId,          // 서비스 ID
    String maxClassNm,     // 대분류명
    String minClassNm,     // 소분류명
    String svcStatNm,      // 서비스 상태명
    String svcNm,          // 서비스명
    String payAtNm,        // 요금 구분명 (무료/유료 등)
    String placeNm,        // 장소명
    String useTgtInfo,     // 이용 대상 정보
    String svcUrl,         // 서비스 URL
    String x,              // X 좌표
    String y,              // Y 좌표
    String svcOpnBgnDt,    // 서비스 운영 시작일시
    String svcOpnEndDt,    // 서비스 운영 종료일시
    String rcptBgnDt,      // 접수 시작일시
    String rcptEndDt,      // 접수 종료일시
    String areaNm,         // 지역명
    String imgUrl,         // 이미지 URL
    String dtlCont,        // 상세내용
    String telNo,          // 전화번호
    String vMin,           // 이용 가능 시작시간
    String vMax,           // 이용 가능 종료시간
    String revStdDayNm,    // 예약 기준 요일명
    String revStdDay       // 예약 기준 요일 (숫자)
) {

}
