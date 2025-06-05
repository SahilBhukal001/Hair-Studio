package com.category.service;

import com.category.domain.Category;
import com.category.dto.CategoryDTO;
import com.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository ;

    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();

        if (categoryDTO.getName() != null) {
            category.setName(categoryDTO.getName());
        }
        if (categoryDTO.getImage() != null) {
            category.setImage(categoryDTO.getImage());
        }
        if (categoryDTO.getSalonId() != null) {
            category.setSalonId(categoryDTO.getSalonId());
        }

        return categoryRepository.save(category);
    }


}
