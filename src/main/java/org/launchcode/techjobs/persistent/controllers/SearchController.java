package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.JobData;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.launchcode.techjobs.persistent.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @Autowired
    private JobRepository jobRepository;

    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);//adds column choices for the search to model
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm){
        Iterable<Job> jobs;
        if (searchTerm.toLowerCase().equals("all") || searchTerm.equals("")){//checks if search term is all or empty
            jobs = jobRepository.findAll();//fetch all jobs
        } else {
            //fetches jobs based on specific search type and term
            jobs = JobData.findByColumnAndValue(searchType, searchTerm, jobRepository.findAll());
        }
        model.addAttribute("columns", columnChoices);//adds colukmn choices to the model
        model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);//sets the title based on criteria
        model.addAttribute("jobs", jobs);//adds list  of jobs tp the model

        return "search";
    }
}
