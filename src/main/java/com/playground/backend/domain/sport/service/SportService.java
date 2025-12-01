package com.playground.backend.domain.sport.service;

import com.playground.backend.domain.sport.dto.request.CreateSportRequest;
import com.playground.backend.domain.sport.entity.Sport;
import com.playground.backend.domain.sport.exception.SportErrorCode;
import com.playground.backend.domain.sport.exception.SportException;
import com.playground.backend.domain.sport.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 스포츠 서비스
 */
@RequiredArgsConstructor
@Service
public class SportService {

    private final SportRepository sportRepository;

    /**
     * 스포츠 생성 메서드
     *
     * @param createSportRequest 스포츠 생성 요청 DTO
     * @throws SportException NAME_EXISTS
     * @throws SportException INVALID_TEAM_MEMBER
     */
    @Transactional
    public void createSport(CreateSportRequest createSportRequest) {
        if (sportRepository.existsByName(createSportRequest.getName())) {
            throw new SportException(SportErrorCode.NAME_EXISTS);
        }

        if (createSportRequest.getMinTeamMember() > createSportRequest.getMaxTeamMember()) {
            throw new SportException(SportErrorCode.INVALID_TEAM_MEMBER);
        }

        Sport sport = Sport.builder()
                .name(createSportRequest.getName())
                .minTeamMember(createSportRequest.getMinTeamMember())
                .maxTeamMember(createSportRequest.getMaxTeamMember())
                .build();

        sportRepository.save(sport);
    }


}
