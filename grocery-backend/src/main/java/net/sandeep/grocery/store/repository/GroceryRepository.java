package net.sandeep.grocery.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.sandeep.grocery.store.model.GroceryItem;

public interface GroceryRepository extends JpaRepository<GroceryItem, Long> {
    
    @Query(value = "SELECT * FROM grocery_item WHERE category_id = ?1", nativeQuery = true)
    List<GroceryItem> getAllByCategoryId(Long categoryId);
    
}
