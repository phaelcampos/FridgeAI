package dev.raphael.fridgeai.repository;

import dev.raphael.fridgeai.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
