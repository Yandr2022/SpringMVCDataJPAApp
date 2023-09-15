package by.Yandr2022.springlearn.repositories;

import by.Yandr2022.springlearn.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmail(String email);
    List<Person> findByNameOrderByAge(String name);
    List<Person> findByName(String name);
    List<Person> findByNameStartingWith(String nameBegin);
    List<Person> findByNameOrEmail(String name, String email);

}
