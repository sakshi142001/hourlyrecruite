package com.hourlyrecruite.hourlyrecruite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hourlyrecruite.hourlyrecruite.model.Job;
import com.hourlyrecruite.hourlyrecruite.repository.JobRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public Job updateJob(Long id, Job updatedJob) {
        return jobRepository.findById(id)
            .map(existingJob -> {
                existingJob.setTitle(updatedJob.getTitle());
                existingJob.setDescription(updatedJob.getDescription());
                existingJob.setLocation(updatedJob.getLocation());
                existingJob.setType(updatedJob.getType());
                existingJob.setSkillsRequired(updatedJob.getSkillsRequired());
                return jobRepository.save(existingJob);
            })
            .orElse(null);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public List<Job> filterJobsBySkill(String skill) {
    return jobRepository.findAll().stream()
            .filter(job -> job.getSkillsRequired() != null && job.getSkillsRequired().contains(skill))
            .collect(Collectors.toList());
}

 
}
