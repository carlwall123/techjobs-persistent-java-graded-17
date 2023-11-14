package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.launchcode.techjobs.persistent.models.JobData;

import java.util.HashMap;
import java.util.List;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
   private SkillRepository skillRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();//hashmap to store column choices for job listings

    public ListController () {
//initialize column choices
        columnChoices.put("all", "All");
        columnChoices.put("employer", "Employer");
        columnChoices.put("skill", "Skill");

    }

    @RequestMapping("")
    public String list(Model model) {
        model.addAttribute("employers", employerRepository.findAll());//adds all employers to model
        model.addAttribute("skills", skillRepository.findAll());//adds all skills to model


        return "list";
    }

    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Job> jobs;
        if (column.toLowerCase().equals("all")){//check if the column is "all"
            jobs = jobRepository.findAll();//fetches all jobs
            model.addAttribute("title", "All Jobs");//sets title for All Jobs
        } else {
            jobs = JobData.findByColumnAndValue(column, value, jobRepository.findAll());//fetches jobs based on specific column and values
            model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);//adds list of jobs tp the model
        }
        model.addAttribute("jobs", jobs);

        return "list-jobs";
    }
}
