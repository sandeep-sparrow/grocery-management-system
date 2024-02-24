package net.sandeep.grocery.store.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.sandeep.grocery.store.dto.GroceryItemDto;
import net.sandeep.grocery.store.exception.ResourceNotFoundException;
import net.sandeep.grocery.store.mapper.GroceryItemMapper;
import net.sandeep.grocery.store.model.Category;
import net.sandeep.grocery.store.model.GroceryItem;
import net.sandeep.grocery.store.repository.CategoryRepository;
import net.sandeep.grocery.store.repository.GroceryRepository;
import net.sandeep.grocery.store.service.GroceryService;

@Service
@AllArgsConstructor
@Slf4j
public class GroceryServiceImpl implements GroceryService{

    private final CacheManager cacheManager;
    private final GroceryRepository groceryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @SuppressWarnings("null")
    public GroceryItemDto createGroceryItem(GroceryItemDto groceryItemDto) {
        GroceryItem groceryItem = 
            GroceryItemMapper.mapToGroceryItem(groceryItemDto);

        Long categoryId = groceryItemDto.getCategoryId();

        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ResourceNotFoundException("Category Item not exists with given categoryId: " + categoryId));

        groceryItem.setCategory(category);
        
        GroceryItem savedGroceryItem = groceryRepository.save(groceryItem);
        return GroceryItemMapper.mapToGroceryItemDto(savedGroceryItem);
    }

    @Override
    @SuppressWarnings("null")
    @Cacheable(cacheNames = "groceryItems", key = "#groceryId")
    public GroceryItemDto getGroceryItemById(Long groceryId) {
        GroceryItem groceryItem = groceryRepository
            .findById(groceryId)
            .orElseThrow(() -> new ResourceNotFoundException("Grocery Item not exists with given groceryId: " + groceryId));
        log.info(String.valueOf(cacheManager.getCacheNames().stream().toList()));
        return GroceryItemMapper.mapToGroceryItemDto(groceryItem);
    }

    @Override
    @Cacheable(cacheNames = "groceryItems", key = "#root.methodName")
    public List<GroceryItemDto> getAllGroceryItems() {
        List<GroceryItem> groceryItems = groceryRepository.findAll();

        return GroceryItemMapper.mapToGroceryItemDtoList(groceryItems);
    }

    @Override
    @CachePut(cacheNames = "groceryItems", key = "#groceryId")
    @SuppressWarnings("null")
    public GroceryItemDto updateGroceryItem(Long groceryId, GroceryItemDto groceryItemDto) {
        GroceryItem groceryItem = groceryRepository.findById(groceryId)
            .orElseThrow(() -> new ResourceNotFoundException("Grocery Item not exists with given groceryId: " + groceryId));

        Long newCategoryId = groceryItemDto.getCategoryId();

        Category newCategory = categoryRepository.findById(newCategoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category Item not exists with given categoryId: " + newCategoryId));

        groceryItem.setCategory(newCategory);
        groceryItem.setGroceryName(groceryItemDto.getGroceryName());
        groceryItem.setGroceryQuantity(groceryItemDto.getGroceryQuantity());
        groceryItem.setGroceryDescription(groceryItemDto.getGroceryDescription());
        groceryItem.setGroceryUnitPrice(groceryItemDto.getGroceryUnitPrice());
        groceryItem.setGroceryUnit(groceryItemDto.getGroceryUnit());

        GroceryItem updatedGroceryItem = groceryRepository.save(groceryItem);

        return GroceryItemMapper.mapToGroceryItemDto(updatedGroceryItem);
    }

    @SuppressWarnings("null")
    @Override
    @CacheEvict(cacheNames = "groceryItems", key = "#groceryId", beforeInvocation = true, condition = "#groceryId != null")
    public void deleteGroceryItem(Long groceryId) {
        groceryRepository.findById(groceryId)
        .orElseThrow(() -> new ResourceNotFoundException("Grocery Item not exists with given groceryId: " + groceryId));

        groceryRepository.deleteById(groceryId);
    }

    @SuppressWarnings("null")
    @Override
    public List<GroceryItemDto> getAllGroceryItemsByCategoryId(Long categoryId) {
        
        categoryRepository.findById(categoryId)
        .orElseThrow(() -> new ResourceNotFoundException("Category Item not exists with given categoryId: " + categoryId));
        List<GroceryItem> groceryItems = groceryRepository.getAllByCategoryId(categoryId);

        return GroceryItemMapper.mapToGroceryItemDtoList(groceryItems);
    }

}
