package com.example.demo.repository;

import com.example.demo.entity.MatchResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchResultRepository extends JpaRepository<MatchResult, Long> {

    List<MatchResult> findByResumeId(Long resumeId);

    List<MatchResult> findByJobId(Long jobId);
}
