package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity{//inherits from abstractentity

    @ManyToOne//defines a manytoone relationship with employer
    private Employer employer;

    @ManyToMany//defines a manytomany relationsship with skill
    private List<Skill> skills;


    public Job() {//default constructor
    }


    public Job(Employer employer, List<Skill> skills)  {
        super();//calls the constructor of the superclass(abstractentity)
        this.employer = employer;
        this.skills = skills;
    }



    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }


    public Iterable<Skill> getSkills() {
        return skills;
    }
    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

}
