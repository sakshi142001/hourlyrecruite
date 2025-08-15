package com.hourlyrecruite.hourlyrecruite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hourlyrecruite.hourlyrecruite.model.Candidate;
import com.hourlyrecruite.hourlyrecruite.model.Job;

import java.util.*;
import java.util.stream.Collectors;

import com.hourlyrecruite.hourlyrecruite.repository.CandidateRepository;
import com.hourlyrecruite.hourlyrecruite.repository.JobRepository;

@Service
public class CandidateService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    public Candidate applyForJob(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public Candidate getProfile(String email) {
        return candidateRepository.findByEmail(email).orElse(null);
    }

    public Candidate updateProfile(String email, Candidate updatedCandidate) {
        Candidate existing = candidateRepository.findByEmail(email).orElse(null);
        if (existing != null) {
            existing.setContactNumber(updatedCandidate.getContactNumber());
            existing.setResume(updatedCandidate.getResume());
        }
        return null;
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public List<Job> matchJobsForCandidate(Candidate candidate) {
        List<Job> allJobs = jobRepository.findAll();
        return allJobs.stream()
                .filter(job -> job.getSkillsRequired() != null &&
                        !Collections.disjoint(candidate.getSkills(), job.getSkillsRequired()))
                .collect(Collectors.toList());
    }

    public Candidate getById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + id));
    }

}
