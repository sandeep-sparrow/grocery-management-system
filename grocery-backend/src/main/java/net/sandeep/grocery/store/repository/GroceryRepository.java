package net.sandeep.grocery.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sandeep.grocery.store.model.GroceryItem;

public interface GroceryRepository extends JpaRepository<GroceryItem, Long> {
    
}
