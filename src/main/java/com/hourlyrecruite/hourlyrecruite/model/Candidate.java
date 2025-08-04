package com.hourlyrecruite.hourlyrecruite.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Candidate {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;
    private String contactNumber;
    private String resume;
    private Double expectedSalary;
    @ElementCollection
    private List<String> skills;
    
    public Candidate(){

    }

    //all args constructor
    public Candidate(Long id,String name, String email, String contactNumber, String resume, Double expectedSalary, List<String> skills){
        this.id=id;
        this.name=name;
        this.email=email;
        this.contactNumber=contactNumber;
        this.resume=resume;
        this.expectedSalary=expectedSalary;
        this.skills=skills;
    }

    
    //setters
    public void setId(Long id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setEmail(String email){
     this.email=email;
    }

    public void setContactNumber(String contactNumber){
        this.contactNumber=contactNumber;
    }

    public void setResume(String resume){
        this.resume=resume;
    }

    public void setExpectedSalary(Double expectedSalary) {
         this.expectedSalary = expectedSalary; 
        }

    public void setSkills(List<String> skills) {
         this.skills = skills; 
    }

    //Getters
    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getContactNumber(){
        return contactNumber;
    }

    public String getResume(){
        return resume;
    }

    public Double getExpectedSalary() { 
        return expectedSalary; 
    }

    public List<String> getSkills() {
         return skills; 
        }


}
