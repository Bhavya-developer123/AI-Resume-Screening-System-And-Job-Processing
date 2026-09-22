package com.example.demo.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.JobRequestDto;
import com.example.demo.dto.JobResponseDto;
import com.example.demo.service.JobService;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobResponseDto> createJob(
            @Valid @RequestBody JobRequestDto request) {

        JobResponseDto response =
                jobService.createJob(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<JobResponseDto>> getAllJobs() {

        List<JobResponseDto> jobs =
                jobService.getAllJobs();

        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDto> getJobById(
            @PathVariable Long id) {

        JobResponseDto response =
                jobService.getJobById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDto> updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobRequestDto request) {

        JobResponseDto response =
                jobService.updateJob(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(
            @PathVariable Long id) {

        jobService.deleteJob(id);

        return ResponseEntity.ok(
                "Job deleted successfully");
    }
}
