package com.hourlyrecruite.hourlyrecruite.model;

import java.util.List;

import jakarta.persistence.*;



@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String type;
    private Double salary;// added when creating matching and filtring module
    //private String skillsRequired

    @ElementCollection
    private List<String> skillsRequired;

    // Constructors
    public Job() {}

    public Job(String title, String description, String location, String type, Double salary, List<String> skillsRequired) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.salary=salary;
        this.skillsRequired = skillsRequired;
    }

    // Getters & Setters

    public Long getId() {
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getTitle() { 
        return title;
     }
    public void setTitle(String title) {
         this.title = title;
         }

    public String getDescription() { 
        return description; 
    }
    public void setDescription(String description) {
         this.description = description; 
        }

    public String getLocation() { 
        return location;
     }
    public void setLocation(String location) {
         this.location = location; 
        }

    public String getType() {
         return type;
         }
    public void setType(String type) {
         this.type = type; 
        }

    public Double getSalary() {
         return salary;
         }
    public void setSalary(Double salary) {
         this.salary = salary; 
        }

    public List<String> getSkillsRequired() { 
        return skillsRequired;
     }
    public void setSkillsRequired(List<String> skillsRequired) {
         this.skillsRequired = skillsRequired;
     }

    
}
