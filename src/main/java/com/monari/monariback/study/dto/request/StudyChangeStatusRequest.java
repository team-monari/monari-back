package com.monari.monariback.study.dto.request;

import com.monari.monariback.study.enumerated.StudyStatus;
import jakarta.validation.constraints.NotNull;

public record StudyChangeStatusRequest(
        @NotNull StudyStatus status
) {
}
