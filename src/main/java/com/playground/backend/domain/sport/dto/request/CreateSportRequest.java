package com.playground.backend.domain.sport.dto.request;

import jakarta.validation.constraints.NotBlank;
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
}
