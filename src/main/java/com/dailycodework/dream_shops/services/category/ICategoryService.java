package com.dailycodework.dream_shops.services.category;

import com.dailycodework.dream_shops.dto.CategoryDto;
import com.dailycodework.dream_shops.models.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    Category addCategory(Category category);
    Category updateCategory(Category category, Long id);
    void deleteCategory(Long id);
    List<Category> getAllCategories();

    List<CategoryDto> getConvertedCategories(List<Category> categories);

    CategoryDto convertToCategoryDto(Category category);
}
