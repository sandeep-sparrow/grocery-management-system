package net.sandeep.grocery.store.service;

import java.util.List;

import net.sandeep.grocery.store.dto.GroceryItemDto;

public interface GroceryService {

    GroceryItemDto createGroceryItem(GroceryItemDto groceryItemDto);
    GroceryItemDto getGroceryItemById(Long groceryId);
    List<GroceryItemDto> getAllGroceryItems();
    GroceryItemDto updateGroceryItem(Long groceryId, GroceryItemDto groceryItemDto);
    void deleteGroceryItem(Long groceryId);

}
