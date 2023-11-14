package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping
    public String displayEmployers(Model model) {
        model.addAttribute("title", "All Employers");//add title atribute to model
        model.addAttribute("employers", employerRepository.findAll());//adds a list of all employers to the model
        return "employers/index";//returns the 'employers/index' view
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());//adds new employer object to the model
        return "employers/add";//returns employers/add view for adding a new employer
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {//check if there are validation errors
            return "employers/add";//if there are errors returns to the add form
        }
        employerRepository.save(newEmployer);//saves new employer in repository
        return "redirect:/employers/add";//Redirects to the 'add' form upon successful save
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional optEmployer = employerRepository.findById(employerId);//retrieves the employer by ID
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();//Gets tge employer if present
            model.addAttribute("employer", employer);//add enmployer to models
            return "employers/view";//return to eployers/view
        } else {
            return "redirect:../";//redirects to main url if employer isnt found
        }
    }
}
