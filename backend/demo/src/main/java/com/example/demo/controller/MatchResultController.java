package com.example.demo.controller;


import com.example.demo.dto.MatchResultRequestDto;
import com.example.demo.entity.MatchResult;
import com.example.demo.service.MatchResultService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/match-results")
public class MatchResultController {

    private final MatchResultService matchResultService;

    public MatchResultController(MatchResultService matchResultService) {
        this.matchResultService = matchResultService;
    }

    @PostMapping
    public ResponseEntity<MatchResult> create(
            @Valid @RequestBody MatchResultRequestDto dto) {

        return ResponseEntity.ok(
                matchResultService.create(dto)
        );
    }

    @GetMapping
    public ResponseEntity<List<MatchResult>> getAll() {

        return ResponseEntity.ok(
                matchResultService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchResult> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                matchResultService.getById(id)
        );
    }

    @GetMapping("/resume/{resumeId}")
    public ResponseEntity<List<MatchResult>> getByResume(
            @PathVariable Long resumeId) {

        return ResponseEntity.ok(
                matchResultService.getByResume(resumeId)
        );
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<MatchResult>> getByJob(
            @PathVariable Long jobId) {

        return ResponseEntity.ok(
                matchResultService.getByJob(jobId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        matchResultService.delete(id);

        return ResponseEntity.ok(
                "Match result deleted successfully"
        );
    }
}
