package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillsController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping
    private String index(Model model) {
        model.addAttribute("title", "All Skills");//sets title attritbute
        model.addAttribute("skills", skillRepository.findAll());//adds a list of all skills to the model
        return "skills/index";
    }

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());//adds a new skill object to model
        return "skills/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {//check if there is validation errors
            return "skills/add";
        }
        skillRepository.save(newSkill);//saves new skill to reposiitory
        return "redirect:/skills/add";
    }

    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        Optional<Skill> optSkill = skillRepository.findById(skillId);//retrieves the skill by ID
        if (optSkill.isPresent()) {
            Skill skill = optSkill.get();//gets the skill if present
            model.addAttribute("skill", skill);//adds the found skill to the model
            return "skills/view";
        } else {
            return "redirect:../";
        }
    }
}
