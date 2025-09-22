package com.playground.backend.domain.sport.repository;

import com.playground.backend.domain.sport.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 스포츠 리포지토리
 */
@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {

}
