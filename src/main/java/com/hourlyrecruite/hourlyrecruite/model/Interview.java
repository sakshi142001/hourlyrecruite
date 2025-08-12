package com.hourlyrecruite.hourlyrecruite.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Interview {
 
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)    
private Long id;

private Long candidateId;

private Long jobId;

private String mode; // online, offline

private String status; // scheduled, completed, cancelled

private LocalDateTime interviewTime;

//setters
public void setId(Long id){
    this.id=id;
}

public void setCnadidateId(Long candidateId){
    this.candidateId=candidateId;
}

public void setJobId(Long jobId){
    this.jobId=jobId;
}

public void setMode(String mode){
    this.mode=mode;
}

public void setStatus(String status){
    this.status=status;
}

public void setInterviewTime(LocalDateTime interviewTime){
    this.interviewTime=interviewTime;
}

//getters
public Long getId(){
    return id;
}

public Long getCandidateId(){
    return candidateId;
}

public Long getJobId() { 
    return jobId;
 }

public String getMode() { 
    return mode; 
}

public String getStatus() {
     return status;
}

public LocalDateTime getInterviewTime() {
     return interviewTime;
}

}
