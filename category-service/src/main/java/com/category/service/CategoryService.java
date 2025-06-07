package com.category.service;

import com.category.domain.Category;
import com.category.dto.CategoryDTO;
import com.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setImage(categoryDTO.getImage());
        category.setSalonId(categoryDTO.getSalonId());

        return categoryRepository.save(category);
    }

    public Set<Category> getAllCategoryBySalonId(Long salonId) {
        return categoryRepository.findBySalonId(salonId);
    }

    public Category getCategoryById(Long categoryId) throws Exception {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new Exception("Category not found with id: " + categoryId));
    }

    public void deleteCategoryById(Category category, Long salonId) throws Exception {
        if (!category.getSalonId().equals(salonId)) {
            throw new Exception("You don't have permission to delete this category");
        }

        categoryRepository.deleteById(category.getId());
    }
}
