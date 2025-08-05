package com.hourlyrecruite.hourlyrecruite.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hourlyrecruite.hourlyrecruite.model.Interview;
import com.hourlyrecruite.hourlyrecruite.repository.InterviewRepository;

@Service
public class InterviewService {

@Autowired
private InterviewRepository interviewRepository;

@Autowired
private NotificationService notificationService;

//Schedule Interview
public Interview scheduleInterview(Interview interview){
    interview.setStatus("scheduled");
    Interview saved = interviewRepository.save(interview);

    // Fetch candidate using candidate ID
    //Candidate candidate = candidateRepository.findById(interview.getCandidateId())
       // .orElseThrow(() -> new RuntimeException("Candidate not found"));

    //return interviewRepository.save(interview);
    notificationService.sendEmail("candidate@email.com", "Interview Scheduled", "Your interview is on " + interview.getInterviewTime());
    notificationService.sendSMS("+919999999999", "Interview scheduled for job " + interview.getJobId());
    notificationService.sendWhatsApp("+919999999999", "WhatsApp: Your interview is scheduled.");

    return saved;
}

//Get all
public List<Interview> getAllInterviews(){
    return interviewRepository.findAll();
}
  
// Get by CandidateId
public List<Interview> getByCandidateId(Long candidateId){
    List<Interview> all = interviewRepository.findAll();
    List<Interview> result = new ArrayList<>();
    for (Interview i : all){
        if (i.getCandidateId()!=null && i.getCandidateId().equals(candidateId)){
            result.add(i);
        }
    }
    return result;
}

//update status
public Interview updateStatus(Long id, String newStatus) {
        List<Interview> all = interviewRepository.findAll();
        for (Interview i : all) {
            if (i.getId().equals(id)) {
                i.setStatus(newStatus);
                return interviewRepository.save(i);
            }
        }
        return null;
    }

}
