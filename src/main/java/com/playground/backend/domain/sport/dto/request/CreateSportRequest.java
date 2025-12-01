package com.playground.backend.domain.sport.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 스포츠 생성 요청 DTO
 */
@Getter
@NoArgsConstructor
public class CreateSportRequest {

    @NotBlank(message = "스포츠 이름을 입력해주세요.")
    private String name;

    @NotNull(message = "최소 팀 인원을 입력해주세요.")
    @Min(value = 1, message = "최소 팀 인원은 1 이상이어야 합니다.")
    private Integer minTeamMember;

    @NotNull(message = "최대 팀 인원을 입력해주세요.")
    @Min(value = 1, message = "최대 팀 인원은 1 이상이어야 합니다.")
    private Integer maxTeamMember;
}
