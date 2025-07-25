package dev.raphael.fridgeai.controller;

import dev.raphael.fridgeai.model.FoodItem;
import dev.raphael.fridgeai.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService foodItemService;
    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping
    public ResponseEntity<FoodItem> createFood(@RequestBody FoodItem foodItem){
        FoodItem food = foodItemService.save(foodItem);
        return ResponseEntity.ok().body(food);
    }

    @GetMapping
    public ResponseEntity<List<FoodItem>> getAllFoodItem(){
        List<FoodItem> foodItems = foodItemService.findAll();
        return ResponseEntity.ok().body(foodItems);
    }

    @GetMapping("{id")
    public ResponseEntity<Optional<FoodItem>> getFoodItemById(@PathVariable Long id){
        Optional<FoodItem> foodItem = foodItemService.findById(id);
        if(foodItem.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(foodItem);
    }

    @DeleteMapping("{id")
    public ResponseEntity<Void> deleteFoodItemById(@RequestParam Long id){
        foodItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id")
    public ResponseEntity<String> updateFoodItemById(@RequestBody FoodItem foodItem,  @PathVariable Long id){
        FoodItem food = foodItemService.update(foodItem, id);
        return ResponseEntity.ok().body(food.getName());
    }

}
