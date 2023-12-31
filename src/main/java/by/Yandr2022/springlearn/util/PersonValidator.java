package by.Yandr2022.springlearn.util;


import by.Yandr2022.springlearn.models.Person;
import by.Yandr2022.springlearn.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> temp = peopleService.findOne(person.getEmail());
        if (temp.isPresent() && temp.get().getId() != person.getId()) {
            errors.rejectValue("email", "", "This email is already taken");
        }
    }
}
