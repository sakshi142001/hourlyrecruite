package com.hourlyrecruite.hourlyrecruite.controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hourlyrecruite.hourlyrecruite.model.Candidate;
import com.hourlyrecruite.hourlyrecruite.model.Job;

import java.util.*;
import com.hourlyrecruite.hourlyrecruite.service.CandidateService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;
    
    // Only users with ROLE_CANDIDATE can apply for a job
    @PostMapping("/apply")
    @PreAuthorize("hasRole('CANDIDATE')")
    public Candidate applyForJob(@RequestBody Candidate candidate) {
        return candidateService.applyForJob(candidate);
    }

   // Only users with ROLE_CANDIDATE can view their own profile
    @GetMapping("/profile")
    @PreAuthorize("hasRole('CANDIDATE')")
    public Candidate viewProfile(@RequestParam String email) {
        return candidateService.getProfile(email);
    }

    @PutMapping("/profile")
    @PreAuthorize("hasRole('CANDIDATE')")
    public Candidate updateProfile(@RequestParam
    String email, @RequestBody Candidate candidate) {
        return candidateService.updateProfile(email, candidate);
    }
    
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/match/{id}")
@PreAuthorize("hasRole('CANDIDATE')")
public List<Job> getMatchingJobs(@PathVariable Long id) {
    Candidate candidate = candidateService.getById(id);
    return candidateService.matchJobsForCandidate(candidate);
}
}