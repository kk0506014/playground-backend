package com.playground.backend.domain.sport.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
}
