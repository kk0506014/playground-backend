package com.playground.backend.domain.sport.service;

import com.playground.backend.domain.sport.dto.request.CreateSportRequest;
import com.playground.backend.domain.sport.dto.request.UpdateSportRequest;
import com.playground.backend.domain.sport.dto.response.SportListResponse;
import com.playground.backend.domain.sport.dto.response.SportResponse;
import com.playground.backend.domain.sport.entity.Sport;
import com.playground.backend.domain.sport.exception.SportErrorCode;
import com.playground.backend.domain.sport.exception.SportException;
import com.playground.backend.domain.sport.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 스포츠 전체 조회 메서드
     *
     * @return SportListResponse DTO List
     */
    @Transactional(readOnly = true)
    public List<SportListResponse> getSportList() {
        return sportRepository.findAll().stream()
                .map(SportListResponse::from)
                .toList();
    }

    /**
     * 스포츠 단건 조회 메서드
     *
     * @param sportId 단건 조회할 스포츠 ID
     * @throws SportException SPORT_NOT_FOUND
     * @return SportResponse DTO
     */
    @Transactional(readOnly = true)
    public SportResponse getSport(Long sportId) {
        Sport sport = sportRepository.findById(sportId)
                .orElseThrow(() -> new SportException(SportErrorCode.SPORT_NOT_FOUND));

        return SportResponse.from(sport);
    }

    /**
     * 스포츠 수정 메서드
     *
     * @param sportId 수정할 스포츠 ID
     * @throws SportException SPORT_NOT_FOUND
     * @throws SportException INVALID_TEAM_MEMBER
     */
    @Transactional
    public void updateSport(Long sportId, UpdateSportRequest updateSportRequest) {
        Sport sport = sportRepository.findById(sportId)
                .orElseThrow(() -> new SportException(SportErrorCode.SPORT_NOT_FOUND));

        Integer min = updateSportRequest.getMinTeamMember();
        Integer max = updateSportRequest.getMaxTeamMember();

        if (min != null && max != null) {
            if (min > max) {
                throw new SportException(SportErrorCode.INVALID_TEAM_MEMBER);
            }
        }

        if (min != null) {
            sport.updateMinTeamMember(min);
        }

        if (max != null) {
            sport.updateMaxTeamMember(max);
        }
    }

    /**
     * 스포츠 삭제 메서드
     *
     * @param sportId 삭제할 스포츠 ID
     * @throws SportException SPORT_NOT_FOUND
     */
    @Transactional
    public void deleteSport(Long sportId) {
        Sport sport = sportRepository.findById(sportId)
                .orElseThrow(() -> new SportException(SportErrorCode.SPORT_NOT_FOUND));

        sportRepository.delete(sport);
    }
}
