package com.playground.backend.domain.sport.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 스포츠 수정 요청 DTO
 */
@Getter
@NoArgsConstructor
public class UpdateSportRequest {

    @Min(value = 1, message = "최소 팀 인원은 1 이상이어야 합니다.")
    private Integer minTeamMember;

    @Min(value = 1, message = "최대 팀 인원은 1 이상이어야 합니다.")
    private Integer maxTeamMember;
}
