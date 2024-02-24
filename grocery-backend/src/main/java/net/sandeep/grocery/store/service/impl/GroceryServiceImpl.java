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
import net.sandeep.grocery.store.model.GroceryItem;
import net.sandeep.grocery.store.repository.GroceryRepository;
import net.sandeep.grocery.store.service.GroceryService;

@Service
@AllArgsConstructor
@Slf4j
public class GroceryServiceImpl implements GroceryService{

    private final CacheManager cacheManager;
    private final GroceryRepository groceryRepository;

    @Override
    public GroceryItemDto createGroceryItem(GroceryItemDto groceryItemDto) {
        GroceryItem groceryItem = 
            GroceryItemMapper.mapToGroceryItem(groceryItemDto);


        GroceryItem savedGroceryItem = groceryRepository.save(groceryItem);
        return GroceryItemMapper.mapToGroceryItemDto(savedGroceryItem);
    }

    @Override
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

}
