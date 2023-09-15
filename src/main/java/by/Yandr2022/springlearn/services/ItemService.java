package by.Yandr2022.springlearn.services;

import by.Yandr2022.springlearn.models.Item;
import by.Yandr2022.springlearn.models.Person;
import by.Yandr2022.springlearn.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemsRepository itemRepository;

    @Autowired
    public ItemService(ItemsRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAllByPerson(Person person) {
        return itemRepository.findByOwner(person);
    }
    public List<Item> findAllByName(String name) {
        return itemRepository.findByName(name);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findOne(int id) {
        return itemRepository.findById(id).orElse(null);
    }


    @Transactional
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void update(int id, Item updatedItem) {
        updatedItem.setId(id);
        itemRepository.save(updatedItem);
    }

    @Transactional
    public void delete(int id) {
        itemRepository.deleteById(id);
    }


}
