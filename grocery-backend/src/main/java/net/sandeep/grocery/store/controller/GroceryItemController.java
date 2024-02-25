package net.sandeep.grocery.store.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import net.sandeep.grocery.store.dto.GroceryItemDto;
import net.sandeep.grocery.store.service.CacheService;
import net.sandeep.grocery.store.service.GroceryService;


@RestController
@RequestMapping("/api/grocery-item")
@CrossOrigin("*")
@AllArgsConstructor
public class GroceryItemController {

    private final GroceryService groceryService;
    private final CacheService cacheService;

    @SuppressWarnings("null")
    @PostMapping("/create")
    public ResponseEntity<GroceryItemDto> createGroceryItem(@RequestBody GroceryItemDto groceryItemDto) {
        GroceryItemDto savedGroceryItem = groceryService.createGroceryItem(groceryItemDto);
        if(savedGroceryItem != null)
            cacheService.removeGroceryItemCache("groceryItems");
        if(savedGroceryItem != null)
            return new ResponseEntity<>(savedGroceryItem, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("/{groceryId}")
    public ResponseEntity<GroceryItemDto> getGroceryItemById(@PathVariable Long groceryId) {
        GroceryItemDto groceryItemDto = groceryService.getGroceryItemById(groceryId);
        return new ResponseEntity<>(groceryItemDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GroceryItemDto>> getAllGroceryItems() {
        List<GroceryItemDto> groceryItemDtos = groceryService.getAllGroceryItems();
        return new ResponseEntity<>(groceryItemDtos, HttpStatus.OK);
    }

    @GetMapping("/getAll/{categoryId}")
    public ResponseEntity<List<GroceryItemDto>> getAllGroceryItemsByCategoryId(@PathVariable Long categoryId) {
        List<GroceryItemDto> groceryItemDtos = groceryService.getAllGroceryItemsByCategoryId(categoryId);
        return new ResponseEntity<>(groceryItemDtos, HttpStatus.OK);
    }

    @SuppressWarnings("null")
    @PutMapping("/update/{groceryId}")
    public ResponseEntity<GroceryItemDto> updateGroceryItem(@PathVariable Long groceryId, 
                                                            @RequestBody GroceryItemDto groceryItemDto) {
        GroceryItemDto updatedGroceryItem = groceryService.updateGroceryItem(groceryId, groceryItemDto);
        if(updatedGroceryItem != null)
            cacheService.removeGroceryItemCache("groceryItems");
        if(updatedGroceryItem != null)
            return new ResponseEntity<>(updatedGroceryItem, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    
    @DeleteMapping("/delete/{groceryId}")
    public ResponseEntity<String> deleteGroceryItem(@PathVariable Long groceryId) {
        groceryService.deleteGroceryItem(groceryId);
        cacheService.removeGroceryItemCache("groceryItems");
        return new ResponseEntity<>("Grocery item deleted successfully groceryId: " + groceryId, HttpStatus.OK);
    }

}
