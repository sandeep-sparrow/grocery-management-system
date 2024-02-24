package net.sandeep.grocery.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sandeep.grocery.store.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
