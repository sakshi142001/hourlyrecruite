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

    @GetMapping("/jobs")
    @PreAuthorize("hasRole('RECRUITER')")
    public String listJobs() {
        return "Here is the list of all jobs.";
    }

    @PostMapping("/jobs")
    @PreAuthorize("hasRole('RECRUITER')")
    public String createJob() {
        return "New job created successfully.";
    }

    @PutMapping("/jobs/{id}")
    @PreAuthorize("hasRole('RECRUITER')")
    public String updateJob(@PathVariable Long id) {
        return "Job with ID " + id + " updated successfully.";
    }

}
