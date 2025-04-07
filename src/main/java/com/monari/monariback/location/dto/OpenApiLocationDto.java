package com.monari.monariback.location.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OpenApiLocationDto(
    @JsonProperty("GUBUN")
    String gubun,

    @JsonProperty("SVCID")
    String svcId,

    @JsonProperty("MAXCLASSNM")
    String maxClassNm,

    @JsonProperty("MINCLASSNM")
    String minClassNm,

    @JsonProperty("SVCSTATNM")
    String svcStatNm,

    @JsonProperty("SVCNM")
    String svcNm,

    @JsonProperty("PAYATNM")
    String payAtNm,

    @JsonProperty("PLACENM")
    String placeNm,

    @JsonProperty("USETGTINFO")
    String useTgtInfo,

    @JsonProperty("SVCURL")
    String svcUrl,

    @JsonProperty("X")
    String x,

    @JsonProperty("Y")
    String y,

    @JsonProperty("SVCOPNBGNDT")
    String svcOpnBgndt,

    @JsonProperty("SVCOPNENDDT")
    String svcOpnEnddt,

    @JsonProperty("RCPTBGNDT")
    String rcptBgndt,

    @JsonProperty("RCPTENDDT")
    String rcptEnddt,

    @JsonProperty("AREANM")
    String areaNm,

    @JsonProperty("IMGURL")
    String imgUrl,

    @JsonProperty("DTLCONT")
    String dtlCont,

    @JsonProperty("TELNO")
    String telNo,

    @JsonProperty("V_MIN")
    String vMin,

    @JsonProperty("V_MAX")
    String vMax,

    @JsonProperty("REVSTDDAYNM")
    String revStdDayNm,

    @JsonProperty("REVSTDDAY")
    String revStdDay
) {

}
