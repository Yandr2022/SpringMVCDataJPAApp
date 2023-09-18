package by.Yandr2022.springlearn.controllers;

import by.Yandr2022.springlearn.dao.PersonDAO;
import by.Yandr2022.springlearn.models.Person;
import by.Yandr2022.springlearn.services.ItemService;
import by.Yandr2022.springlearn.services.PeopleService;
import by.Yandr2022.springlearn.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ItemService itemService;
    private final PersonValidator personValidator;
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PeopleService peopleService, ItemService itemService, PersonValidator personValidator
            , PersonDAO personDAO) {
        this.peopleService = peopleService;
        this.itemService = itemService;
        this.personValidator = personValidator;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
//        model.addAttribute("people", peopleService.findAll());
personDAO.testNPlus1();
//        itemService.findAllByName("Airpods"); debug
//        itemService.findAllByPerson(peopleService.findAll().get(0));
//        peopleService.test();
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {

        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/edit";

        peopleService.update(id,person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
       peopleService.delete(id);
        return "redirect:/people";
    }
}
