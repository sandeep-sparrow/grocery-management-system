package net.sandeep.grocery.store.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sandeep.grocery.store.dto.CategoryDto;
import net.sandeep.grocery.store.mapper.CategoryMapper;
import net.sandeep.grocery.store.model.Category;
import net.sandeep.grocery.store.repository.CategoryRepository;
import net.sandeep.grocery.store.service.CategoryService;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws IllegalArgumentException {
        Category category = CategoryMapper.mapToCategory(categoryDto);

        if(category != null){
            Category savCategory = categoryRepository.save(category);
            return CategoryMapper.mapToCategoryDto(savCategory);
        }else{
            throw new IllegalArgumentException("Invalid or Empty Category requested!");
        }
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) throws IllegalArgumentException {
        if(categoryId != null)
            return CategoryMapper.mapToCategoryDto(categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found!"))); 
        else
            throw new IllegalArgumentException("Invalid or Empty Category Id requested!");
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return CategoryMapper.mapToCategoryDtoList(categories);
    }

    @Override
    @SuppressWarnings("null")
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new IllegalArgumentException("Category not found!"));
            
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        categoryRepository.save(category);

        return CategoryMapper.mapToCategoryDto(category);   
    }

    @Override
    @SuppressWarnings("null")
    public void deleteCategory(Long categoryId) {

        Category category = categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new IllegalArgumentException("Category not found!"));
        
        categoryRepository.delete(category);
    }
    
}
