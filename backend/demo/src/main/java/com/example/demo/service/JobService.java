package com.example.demo.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.JobRequestDto;
import com.example.demo.dto.JobResponseDto;
import com.example.demo.entity.Job;
import com.example.demo.entity.User;
import com.example.demo.repository.JobRepository;
import com.example.demo.repository.UserRepository;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(
            JobRepository jobRepository,
            UserRepository userRepository) {

        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public JobResponseDto createJob(JobRequestDto request) {

        User recruiter = userRepository.findById(request.getRecruiterId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Recruiter not found with id: "
                                        + request.getRecruiterId()));

        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setRequiredSkills(request.getRequiredSkills());
        job.setPreferredSkills(request.getPreferredSkills());
        job.setExperience(request.getExperience());
        job.setLocation(request.getLocation());
        job.setEmploymentType(request.getEmploymentType());
        job.setRecruiter(recruiter);
        job.setCreatedAt(LocalDateTime.now());

        Job savedJob = jobRepository.save(job);

        return convertToDto(savedJob);
    }

    public List<JobResponseDto> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public JobResponseDto getJobById(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Job not found with id: " + id));

        return convertToDto(job);
    }

    public JobResponseDto updateJob(
            Long id,
            JobRequestDto request) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Job not found with id: " + id));

        User recruiter = userRepository.findById(request.getRecruiterId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Recruiter not found with id: "
                                        + request.getRecruiterId()));

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setRequiredSkills(request.getRequiredSkills());
        job.setPreferredSkills(request.getPreferredSkills());
        job.setExperience(request.getExperience());
        job.setLocation(request.getLocation());
        job.setEmploymentType(request.getEmploymentType());
        job.setRecruiter(recruiter);

        Job updatedJob = jobRepository.save(job);

        return convertToDto(updatedJob);
    }

    public void deleteJob(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Job not found with id: " + id));

        jobRepository.delete(job);
    }

    private JobResponseDto convertToDto(Job job) {

        return new JobResponseDto(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getRequiredSkills(),
                job.getPreferredSkills(),
                job.getExperience(),
                job.getLocation(),
                job.getEmploymentType(),
                job.getRecruiter().getId(),
                job.getCreatedAt()
        );
    }
}
