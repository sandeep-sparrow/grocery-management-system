package net.sandeep.grocery.store.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create New Grocery Item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "GroceryItem Creation Was Success!",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GroceryItemDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Grocery Supplied",
            content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid Grocery Supplied",
                    content = @Content),
    })
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
    @Operation(summary = "Get Grocery by Grocery Item Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Grocery Was Success!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GroceryItemDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Grocery Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid Grocery Id Supplied",
                    content = @Content),
    })
    public ResponseEntity<GroceryItemDto> getGroceryItemById(@PathVariable Long groceryId) {
        GroceryItemDto groceryItemDto = groceryService.getGroceryItemById(groceryId);
        return new ResponseEntity<>(groceryItemDto, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get All Grocery")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Grocery Was Success!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GroceryItemDto.class))}),
            @ApiResponse(responseCode = "400", description = "Some Error occured!",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Some Error occured!",
                    content = @Content),
    })
    public ResponseEntity<List<GroceryItemDto>> getAllGroceryItems() {
        List<GroceryItemDto> groceryItemDtos = groceryService.getAllGroceryItems();
        return new ResponseEntity<>(groceryItemDtos, HttpStatus.OK);
    }

    @GetMapping("/getAll/{categoryId}")
    @Operation(summary = "Get All Grocery by Category Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get All Grocery by Category Id Was Success!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GroceryItemDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Category Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid Category Id Supplied",
                    content = @Content),
    })
    public ResponseEntity<List<GroceryItemDto>> getAllGroceryItemsByCategoryId(@PathVariable Long categoryId) {
        List<GroceryItemDto> groceryItemDtos = groceryService.getAllGroceryItemsByCategoryId(categoryId);
        return new ResponseEntity<>(groceryItemDtos, HttpStatus.OK);
    }

    @SuppressWarnings("null")
    @PutMapping("/update/{groceryId}")
    @Operation(summary = "Update Grocery by Grocery Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update Grocery by Grocery Id Was Success!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GroceryItemDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Grocery Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid Grocery Id Supplied",
                    content = @Content),
    })
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
    @Operation(summary = "Delete Grocery by Grocery Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete Grocery by Grocery Id Was Success!",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GroceryItemDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Grocery Id Supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid Grocery Id Supplied",
                    content = @Content),
    })
    public ResponseEntity<String> deleteGroceryItem(@PathVariable Long groceryId) {
        groceryService.deleteGroceryItem(groceryId);
        cacheService.removeGroceryItemCache("groceryItems");
        return new ResponseEntity<>("Grocery item deleted successfully groceryId: " + groceryId, HttpStatus.OK);
    }

}
