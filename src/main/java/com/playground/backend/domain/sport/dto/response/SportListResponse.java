package com.playground.backend.domain.sport.dto.response;

import com.playground.backend.domain.sport.entity.Sport;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 스포츠 전체 조회 DTO
 */
@AllArgsConstructor
@Getter
public class SportListResponse {

    private Long id;

    private String name;

    /**
     * SportListResponse DTO 변환 메서드
     *
     * @param sport 변환할 Sport 엔티티
     * @return SportListResponse DTO
     */
    public static SportListResponse from(Sport sport) {
        return new SportListResponse(
                sport.getId(),
                sport.getName()
        );
    }
}
