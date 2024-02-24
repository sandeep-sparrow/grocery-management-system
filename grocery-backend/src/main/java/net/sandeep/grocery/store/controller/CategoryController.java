package net.sandeep.grocery.store.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.sandeep.grocery.store.dto.CategoryDto;
import net.sandeep.grocery.store.service.CategoryService;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savCategoryDto = categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(savCategoryDto, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") Long categoryId){
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);

        return new ResponseEntity<>(categoryDto, HttpStatus.OK);  //200 ok status code
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();

        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);  //200 ok status code
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("categoryId") Long categoryId, @RequestBody CategoryDto categoryDto){
        CategoryDto updatedCategoryDto = categoryService.updateCategory(categoryId, categoryDto);

        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long categoryId){
        categoryService.deleteCategory(categoryId);

        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);  //200 ok status code
    }
    
}
