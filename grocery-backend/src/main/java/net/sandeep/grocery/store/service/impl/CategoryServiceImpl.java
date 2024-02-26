package net.sandeep.grocery.store.service.impl;

import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sandeep.grocery.store.dto.CategoryDto;
import net.sandeep.grocery.store.mapper.CategoryMapper;
import net.sandeep.grocery.store.model.Category;
import net.sandeep.grocery.store.repository.CategoryRepository;
import net.sandeep.grocery.store.service.CategoryService;

@Service
@AllArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CacheManager cacheManager;
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws IllegalArgumentException {
        Category category = CategoryMapper.mapToCategory(categoryDto);
        Category savCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(savCategory);
    }

    @Override
    @Cacheable(cacheNames = "categories", key = "#categoryId")
    @SuppressWarnings("null")
    public CategoryDto getCategoryById(Long categoryId) throws IllegalArgumentException {
        Category category = categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new IllegalArgumentException("Category not found!"));
        log.info(String.valueOf(cacheManager.getCacheNames().stream().toList()));
        return CategoryMapper.mapToCategoryDto(category);
    }

    @Override
    @Cacheable(cacheNames = "categories", key = "#root.methodName")
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return CategoryMapper.mapToCategoryDtoList(categories);
    }

    @Override
    @SuppressWarnings("null")
    @CachePut(cacheNames = "categories", key = "#categoryId")
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category category = categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new IllegalArgumentException("Category not found!"));
            
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        categoryRepository.save(category);

        return CategoryMapper.mapToCategoryDto(category);   
    }

    @SuppressWarnings("null")
    @Override
    @CacheEvict(cacheNames = "categories", key = "#categoryId", beforeInvocation = true, condition = "#categoryId != null")
    public void deleteCategory(Long categoryId) {

        Category category = categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new IllegalArgumentException("Category not found!"));
        
        categoryRepository.delete(category);
    }
    
}
