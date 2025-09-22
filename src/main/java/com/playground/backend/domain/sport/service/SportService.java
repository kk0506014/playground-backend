package com.playground.backend.domain.sport.service;

import com.playground.backend.domain.sport.dto.request.CreateSportRequest;
import com.playground.backend.domain.sport.entity.Sport;
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
     */
    @Transactional
    public void createSport(CreateSportRequest createSportRequest) {

        Sport sport = Sport.builder()
                .name(createSportRequest.getName())
                .build();

        sportRepository.save(sport);
    }
}
