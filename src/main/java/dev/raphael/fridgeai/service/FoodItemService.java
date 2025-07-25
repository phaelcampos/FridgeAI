package dev.raphael.fridgeai.service;

import dev.raphael.fridgeai.model.FoodItem;
import dev.raphael.fridgeai.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItem) {
        this.foodItemRepository = foodItem;
    }

    public FoodItem save(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> findAll() {
        return foodItemRepository.findAll();
    }

    public Optional<FoodItem> findById(Long id) {
        return foodItemRepository.findById(id);
    }

    public FoodItem update(FoodItem updatedFoodItem, Long id) {
        if  (foodItemRepository.findById(id).isPresent()) {
            updatedFoodItem.setId(id);
            return foodItemRepository.save(updatedFoodItem);
        }
        return null;
    }

    public void deleteById(Long id) {
        foodItemRepository.deleteById(id);
    }
}
