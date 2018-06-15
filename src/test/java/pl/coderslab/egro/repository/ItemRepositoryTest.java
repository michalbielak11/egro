package pl.coderslab.egro.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.egro.entity.Item;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

public class ItemRepositoryTest {
    @Autowired
    private  EntityManager entityManager;
    @Autowired
    private  ItemRepository itemRepository;



//    @Test
//    public void find_by_name_and_return_item() {
//    //given
//        Item item = new Item();
//        item.setName("myszka");
//        entityManager.persist(item);
//        // when
//        Item result = itemRepository.findItemByName("myszka");
//        //then
//        assertEquals(result.getName(), item.getName());
//    }
//    @Test
//    public void find_by_id_and_return_item() {
//        //given
//        Item item = new Item();
//        item.setId(5l);
//        entityManager.persist(item);
//        //when
//        Item result = itemRepository.findOne(5l);
//        assertEquals(result.getId(), item.getId());
//
//    }
}
