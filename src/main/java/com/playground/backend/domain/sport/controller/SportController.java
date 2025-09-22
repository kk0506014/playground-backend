package com.playground.backend.domain.sport.controller;

import com.playground.backend.domain.sport.dto.request.CreateSportRequest;
import com.playground.backend.domain.sport.service.SportService;
import com.playground.backend.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 스포츠 컨트롤러
 */
@Tag(name = "스포츠")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sports")
public class SportController {

    private final SportService sportService;

    /**
     * 스포츠 생성 엔드포인트
     *
     * @param createSportRequest 스포츠 생성 요청 DTO
     * @return 성공 시 성공 메시지, 실패 시 에러 메시지
     */
    @PostMapping
    @Operation(summary = "스포츠 생성")
    public ResponseEntity<ApiResponse<String>> createSport(
            @Valid @RequestBody CreateSportRequest createSportRequest) {
        sportService.createSport(createSportRequest);

        return ResponseEntity.ok(ApiResponse.success("스포츠 생성 성공"));
    }
}
