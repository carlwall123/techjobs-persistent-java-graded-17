package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {//inherits from abstractentity class

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description must be less than 500 characters.")
    private String description;
    
    @ManyToMany(mappedBy = "skills")//defines a manyToMany relationship with the job entity
    private List<Job> jobs = new ArrayList<>();//list to hold associated jobs

    public Skill(){}


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

}
