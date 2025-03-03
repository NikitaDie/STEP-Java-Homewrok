package com.example.nikitadev.hs02172025.controller;

import com.example.nikitadev.hs02172025.model.Person;
import com.example.nikitadev.hs02172025.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String listPersons(@RequestParam(name = "query") Optional<String> queryOpt, Model model) {
        if (queryOpt.isPresent()) {
            model.addAttribute("persons", personService.searchPeopleByName(queryOpt.get()));
        } else {
            model.addAttribute("persons", personService.getAllPeople());
        }
        model.addAttribute("query", queryOpt.orElse(""));
        return "index";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("person", new Person());
        return "add-person";
    }

    @PostMapping("/add")
    public String addPerson(@ModelAttribute Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "add-person";

        personService.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String showPersonDetails(@PathVariable UUID id, Model model) {
        Optional<Person> personOpt = personService.getPersonById(id);
        if (personOpt.isEmpty()) {
            return "redirect:/people";
        }
        model.addAttribute("person", personOpt.get());
        return "person-details";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable UUID id, Model model) {
        Optional<Person> personOpt = personService.getPersonById(id);
        if (personOpt.isEmpty()) {
            return "redirect:/people";
        }
        model.addAttribute("person", personOpt.get());
        return "edit-person";
    }

    @PostMapping("/edit/{id}")
    public String editPerson(@PathVariable UUID id, @ModelAttribute Person updatedPerson, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "edit-person";

        personService.updatePerson(id, updatedPerson);
        return "redirect:/people";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable UUID id) {
        personService.deletePerson(id);
        return "redirect:/people";
    }

}
