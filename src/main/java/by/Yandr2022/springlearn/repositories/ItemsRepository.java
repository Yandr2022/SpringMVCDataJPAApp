package by.Yandr2022.springlearn.repositories;

import by.Yandr2022.springlearn.models.Item;
import by.Yandr2022.springlearn.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
    List<Item> findByOwner(Person owner);
    List<Item> findByName(String name);

}
