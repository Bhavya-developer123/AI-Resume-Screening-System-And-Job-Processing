package com.example.demo.service;


import com.example.demo.dto.MatchResultRequestDto;
import com.example.demo.entity.Job;
import com.example.demo.entity.MatchResult;
import com.example.demo.entity.Resume;
import com.example.demo.repository.JobRepository;
import com.example.demo.repository.MatchResultRepository;
import com.example.demo.repository.ResumeRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchResultService {

    private final MatchResultRepository matchResultRepository;
    private final ResumeRepository resumeRepository;
    private final JobRepository jobRepository;

    public MatchResultService(
            MatchResultRepository matchResultRepository,
            ResumeRepository resumeRepository,
            JobRepository jobRepository) {

        this.matchResultRepository = matchResultRepository;
        this.resumeRepository = resumeRepository;
        this.jobRepository = jobRepository;
    }

    public MatchResult create(MatchResultRequestDto dto) {

        Resume resume = resumeRepository.findById(dto.getResumeId())
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        Job job = jobRepository.findById(dto.getJobId())
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        MatchResult result = new MatchResult();

        result.setResume(resume);
        result.setJob(job);
        result.setMatchScore(dto.getMatchScore());
        result.setMatchedSkills(dto.getMatchedSkills());
        result.setMissingSkills(dto.getMissingSkills());
        result.setRecommendations(dto.getRecommendations());
        result.setCreatedAt(LocalDateTime.now());

        return matchResultRepository.save(result);
    }

    public List<MatchResult> getAll() {
        return matchResultRepository.findAll();
    }

    public MatchResult getById(Long id) {

        return matchResultRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Match result not found"));
    }

    public List<MatchResult> getByResume(Long resumeId) {
        return matchResultRepository.findByResumeId(resumeId);
    }

    public List<MatchResult> getByJob(Long jobId) {
        return matchResultRepository.findByJobId(jobId);
    }

    public void delete(Long id) {

        MatchResult result = getById(id);

        matchResultRepository.delete(result);
    }
}
