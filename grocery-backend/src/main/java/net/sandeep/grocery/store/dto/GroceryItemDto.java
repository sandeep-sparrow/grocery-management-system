package net.sandeep.grocery.store.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroceryItemDto {

    private Long groceryId;
    private String groceryName;
    private String groceryDescription;
    private int groceryQuantity;
    private int groceryUnit;
    private double groceryUnitPrice;
    
}
