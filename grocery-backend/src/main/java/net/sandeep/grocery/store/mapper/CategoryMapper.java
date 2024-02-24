package net.sandeep.grocery.store.mapper;

import java.util.List;

import net.sandeep.grocery.store.dto.CategoryDto;
import net.sandeep.grocery.store.model.Category;

public class CategoryMapper {

    public static CategoryDto mapToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(category.getCategoryId());
        categoryDto.setCategoryName(category.getCategoryName());
        categoryDto.setCategoryDescription(category.getCategoryDescription());

        return categoryDto;
    }

    public static Category mapToCategory(CategoryDto categoryDto){
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        category.setCategoryDescription(categoryDto.getCategoryDescription());

        return category;
    }

    public static List<CategoryDto> mapToCategoryDtoList(List<Category> categories) {
        return categories.stream().map(CategoryMapper::mapToCategoryDto).toList();
    }
    
}
