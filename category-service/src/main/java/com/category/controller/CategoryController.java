package com.category.controller;

import com.category.domain.Category;
import com.category.dto.CategoryDTO;
import com.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService ;

    // this is specific for salon owner
    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {

        // Get current userId -> current user salon id -> save category in that salon

        Category createdCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(createdCategory);
    }

    @GetMapping("/salon/{salonId}")
    public ResponseEntity<Set<Category>> getAllBySalonId(@PathVariable Long salonId) {
        Set<Category> categories = categoryService.getAllCategoryBySalonId(salonId);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        try {
            Category category = categoryService.getCategoryById(categoryId);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // this is specific for salon owner
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Long categoryId,
            @RequestParam Long salonId) {

        try {
            Category category = categoryService.getCategoryById(categoryId);
            categoryService.deleteCategoryById(category, salonId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

}
