package by.Yandr2022.springlearn.dao;

import by.Yandr2022.springlearn.models.Person;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {

        Session session = entityManager.unwrap(Session.class);
//        List<Person> personList = session.createQuery("select p from Person p", Person.class).getResultList();
//        for (Person p : personList) {
//            System.out.println("Person " + p.getName() + " has: " + p.getItems());
//        }
        Set<Person> personList = new HashSet<Person>(session.createQuery("select p from Person p LEFT join Fetch p.items").getResultList()) ;
        for (Person p : personList) {
            System.out.println("Person " + p.getName() + " has: " + p.getItems());
        }
    }

}
