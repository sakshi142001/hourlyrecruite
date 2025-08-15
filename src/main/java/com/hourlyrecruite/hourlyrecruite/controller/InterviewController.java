package com.hourlyrecruite.hourlyrecruite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hourlyrecruite.hourlyrecruite.model.Interview;
import com.hourlyrecruite.hourlyrecruite.service.InterviewService;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    public Interview scheduleInterview(@RequestBody Interview interview) {
        return interviewService.scheduleInterview(interview);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Interview> getAllInterviews() {
        return interviewService.getAllInterviews();
    }

    @GetMapping("/candidate/{candidateId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CANDIDATE')")
    public List<Interview> getByCandidateId(@PathVariable Long candidateId) {
        return interviewService.getByCandidateId(candidateId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECRUITER')")
    public Interview updateInterviewStatus(@PathVariable Long id, @RequestParam String status) {
        return interviewService.updateStatus(id, status);
    }

}
