package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");//adds a title attribute to model

        List jobs = (List<Job>) jobRepository.findAll();//retrieve all jobs
        model.addAttribute("jobs", jobs );//adds list of jobs to model

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");//set page title
        model.addAttribute(new Job());//adds a new job object to the model
        model.addAttribute("employers", employerRepository.findAll());//adds a list of all employers
        model.addAttribute("skills", skillRepository.findAll());//adds a list of all skills

        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

        if (errors.hasErrors()) {//check for validation errors
            return "add";
        }

        Employer result = employerRepository.findById(employerId).orElse(new Employer());//finds employer by id
            newJob.setEmployer(result);//set employer for the new job

        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);//finds skills by ids
        newJob.setSkills(skillObjs);//sets skills for new job

        jobRepository.save(newJob);//saves new job to the repository

        return "redirect:/add";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional optJob = jobRepository.findById(jobId);//retrive job by id
        if (optJob.isPresent()) {
            Job job = (Job) optJob.get();//gets job if it is present
            model.addAttribute("job", job);//adds found job to the model
            return "view";//returns the view for the job
        } else {
            return "redirect:../";
        }
    }

}
