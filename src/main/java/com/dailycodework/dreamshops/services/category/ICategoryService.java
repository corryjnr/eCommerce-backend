package com.dailycodework.dreamshops.services.category;

import com.dailycodework.dreamshops.dto.CategoryDto;
import com.dailycodework.dreamshops.models.Category;

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
