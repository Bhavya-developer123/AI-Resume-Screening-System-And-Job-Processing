package com.example.demo.dto;


import java.time.LocalDateTime;

public class JobResponseDto {

    private Long id;
    private String title;
    private String description;
    private String requiredSkills;
    private String preferredSkills;
    private String experience;
    private String location;
    private String employmentType;
    private Long recruiterId;
    private LocalDateTime createdAt;

    public JobResponseDto() {
    }

    public JobResponseDto(
            Long id,
            String title,
            String description,
            String requiredSkills,
            String preferredSkills,
            String experience,
            String location,
            String employmentType,
            Long recruiterId,
            LocalDateTime createdAt) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.preferredSkills = preferredSkills;
        this.experience = experience;
        this.location = location;
        this.employmentType = employmentType;
        this.recruiterId = recruiterId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public String getPreferredSkills() {
        return preferredSkills;
    }

    public String getExperience() {
        return experience;
    }

    public String getLocation() {
        return location;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public Long getRecruiterId() {
        return recruiterId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
