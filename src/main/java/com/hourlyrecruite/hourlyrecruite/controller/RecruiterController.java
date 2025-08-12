package com.hourlyrecruite.hourlyrecruite.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    //Only users with ROLE_RECRUITER can view the job list
    @GetMapping("/jobs")
    @PreAuthorize("hasRole('RECRUITER')")
    public String listJobs() {
        return "Here is the list of all jobs.";
    }

    // Only users with ROLE_RECRUITER can create a new job
    @PostMapping("/jobs")
    @PreAuthorize("hasRole('RECRUITER')")
    public String createJob() {
        return "New job created successfully.";
    }

    // Only users with ROLE_RECRUITER can update an existing job
    @PutMapping("/jobs/{id}")
    @PreAuthorize("hasRole('RECRUITER')")
    public String updateJob(@PathVariable Long id) {
        return "Job with ID " + id + " updated successfully.";
    }

    
}
