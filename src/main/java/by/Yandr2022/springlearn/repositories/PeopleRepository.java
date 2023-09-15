package by.Yandr2022.springlearn.repositories;

import by.Yandr2022.springlearn.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findPeopleByEmail(String email);
}