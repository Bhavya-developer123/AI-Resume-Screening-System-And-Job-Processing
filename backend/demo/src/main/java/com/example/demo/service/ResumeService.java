package com.example.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Repository.ResumeRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.dto.ResumeResponseDto;
import com.example.demo.entity.Resume;
import com.example.demo.entity.User;

import java.nio.file.Path;
@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    private final Path uploadDirectory =
            Paths.get("uploads/resumes");

    public ResumeService(
            ResumeRepository resumeRepository,
            UserRepository userRepository) {

        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }

    public ResumeResponseDto uploadResume(Long userId,MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Resume file is required");
        }
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isBlank()) {
            throw new IllegalArgumentException("Invalid file name");
        }
        String lowerCaseFileName =originalFileName.toLowerCase();
        if (!lowerCaseFileName.endsWith(".pdf")
                && !lowerCaseFileName.endsWith(".docx")) {
            throw new IllegalArgumentException(
                    "Only PDF and DOCX files are allowed");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + userId));
        Files.createDirectories(uploadDirectory);
        String uniqueFileName =
                UUID.randomUUID() + "_" + originalFileName;
        Path filePath =
                uploadDirectory.resolve(uniqueFileName);
        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING);
        Resume resume = new Resume();
        resume.setUser(user);
        resume.setFileName(originalFileName);
        resume.setFilePath(filePath.toString());
        resume.setUploadedAt(LocalDateTime.now());
        Resume savedResume =resumeRepository.save(resume);
        return convertToDto(savedResume);
    }

    public ResumeResponseDto getResumeById(Long id) {
        Resume resume = resumeRepository.findById(id).orElseThrow(() ->new RuntimeException("Resume not found with id: " + id));
        return convertToDto(resume);
    }

    public List<ResumeResponseDto> getUserResumes(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        return resumeRepository.findByUserId(userId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public void deleteResume(Long id) throws IOException {
        Resume resume = resumeRepository.findById(id).orElseThrow(() ->new RuntimeException("Resume not found with id: " + id));
        Path filePath = Paths.get(resume.getFilePath());
        Files.deleteIfExists(filePath);
        resumeRepository.delete(resume);
    }

    private ResumeResponseDto convertToDto(Resume resume) {
        return new ResumeResponseDto(resume.getId(),resume.getUser().getId(),resume.getFileName(),
                                     resume.getFilePath(),resume.getUploadedAt());
    }
}
