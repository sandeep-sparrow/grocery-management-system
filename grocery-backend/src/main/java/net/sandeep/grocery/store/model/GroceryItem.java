package net.sandeep.grocery.store.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.sandeep.grocery.store.dto.GroceryStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "grocery_item")
@Table(name = "grocery_item")
public class GroceryItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grocery_id", nullable = false, unique = true, updatable = false, insertable = false)
    private Long groceryId;

    @Column(name = "grocery_name", nullable = false)
    private String groceryName;

    @Column(name = "grocery_description", nullable = false)
    private String groceryDescription;

    @Column(name = "grocery_quantity", nullable = false)
    private int groceryQuantity;

    @Column(name = "grocery_unit", nullable = false)
    private int groceryUnit;

    @Column(name = "grocery_unit_price", nullable = false)
    private double groceryUnitPrice;

    @Column(name = "grocery_status", nullable = false)
    private GroceryStatus groceryStatus;

    @Column(name = "grocery_created_date", nullable = false)
    private LocalDate groceryCreatedDate;

    @Column(name = "grocery_updated_date", nullable = false)
    private LocalDate groceryUpdatedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
}
