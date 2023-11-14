package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import javax.print.attribute.standard.JobHoldUntil;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {
    @NotBlank(message = "Location is required.")
    @Size(max = 50, message = "Location must be less than 50 characters.")
    private String location;

    @OneToMany
    @JoinColumn(name = "employer_id")
    private  List<Job> jobs = new ArrayList<>();

    public Employer() {}


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public List<Job> getJobs(){
    return jobs;
 }
    public void setJobs(List<Job>jobs){ this.jobs = jobs;}
}
