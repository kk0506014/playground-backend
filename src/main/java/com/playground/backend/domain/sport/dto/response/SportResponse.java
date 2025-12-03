package com.playground.backend.domain.sport.dto.response;

import com.playground.backend.domain.sport.entity.Sport;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 스포츠 단건 조회 DTO
 */
@AllArgsConstructor
@Getter
public class SportResponse {

    private Long id;

    private String name;

    private Integer minTeamMember;

    private Integer maxTeamMember;

    /**
     * SportResponse DTO 변환 메서드
     *
     * @param sport 변환할 Sport 엔티티
     * @return SportResponse DTO
     */
    public static SportResponse from(Sport sport) {
        return new SportResponse(
                sport.getId(),
                sport.getName(),
                sport.getMinTeamMember(),
                sport.getMaxTeamMember()
        );
    }
}
