package net.sandeep.grocery.store.mapper;

import net.sandeep.grocery.store.dto.GroceryItemDto;
import net.sandeep.grocery.store.dto.GroceryStatus;
import net.sandeep.grocery.store.model.GroceryItem;
import java.time.LocalDate;
import java.util.List;

public class GroceryItemMapper {

    public static GroceryItemDto mapToGroceryItemDto(GroceryItem groceryItem){
        return new GroceryItemDto(
            groceryItem.getGroceryId(),
            groceryItem.getGroceryName(),
            groceryItem.getGroceryDescription(),
            groceryItem.getGroceryQuantity(),
            groceryItem.getGroceryUnit(),
            groceryItem.getGroceryUnitPrice(),
            groceryItem.getCategory().getCategoryId()
        );
    }

    public static GroceryItem mapToGroceryItem(GroceryItemDto groceryItemDto){
        return new GroceryItem(
            groceryItemDto.getGroceryId(),
            groceryItemDto.getGroceryName(),
            groceryItemDto.getGroceryDescription(),
            groceryItemDto.getGroceryQuantity(),
            groceryItemDto.getGroceryUnit(),
            groceryItemDto.getGroceryUnitPrice(),
            GroceryStatus.ACTIVE,
            LocalDate.now(),
            LocalDate.now(),
            null
        );
    }

    public static List<GroceryItemDto> mapToGroceryItemDtoList(List<GroceryItem> groceryItems) {
        return groceryItems.stream().map(GroceryItemMapper::mapToGroceryItemDto).toList();
    }
    
}
