package pl.coderslab.egro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.egro.entity.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findItemById (long id);

    Item findItemByName (String name);

    @Query("select u from Item u where u.name like %?1%")
    List<Item> findItemsContaining(String param);
    @Query("select u from Item u order by u.price asc")
    List<Item> itemsSortedByPriceAsc();
    @Query("select u from Item u order by u.price desc")
    List<Item> itemsSortedByPriceDasc();
    @Query("select u from Item u order by u.name asc")
    List<Item> itemsSortedByNameAsc();
    @Query("select u from Item u order by u.name desc")
    List<Item> itemsSortedByNameDasc();


}
