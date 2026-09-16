package com.example.demo.dto;

import java.time.LocalDateTime;

public class ResumeResponseDto {
    private Long id;
    private Long userId;
    private String fileName;
    private String filePath;
    private LocalDateTime uploadedAt;
    public ResumeResponseDto() {
    }
    public ResumeResponseDto(Long id,Long userId,String fileName,String filePath,LocalDateTime uploadedAt) {
        this.id = id;
        this.userId = userId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.uploadedAt = uploadedAt;
    }
    public Long getId() {
        return id;
    }
    public Long getUserId() {
        return userId;
    }
    public String getFileName() {
        return fileName;
    }
    public String getFilePath() {
        return filePath;
    }
    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }
}
