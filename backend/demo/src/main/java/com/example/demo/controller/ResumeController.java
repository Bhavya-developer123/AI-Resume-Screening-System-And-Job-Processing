package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ResumeResponseDto;
import com.example.demo.service.ResumeService;
@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    private final ResumeService resumeService;
    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResumeResponseDto> uploadResume(@RequestParam("userId") Long userId,@RequestParam("file") MultipartFile file)throws IOException {
        ResumeResponseDto response =resumeService.uploadResume(userId, file);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeResponseDto> getResume( @PathVariable Long id) {
        ResumeResponseDto response =resumeService.getResumeById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ResumeResponseDto>> getUserResumes(@PathVariable Long userId) {
        List<ResumeResponseDto> resumes =resumeService.getUserResumes(userId);
        return ResponseEntity.ok(resumes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResume(@PathVariable Long id)throws IOException {
        resumeService.deleteResume(id);
        return ResponseEntity.ok("Resume deleted successfully");
    }
}
