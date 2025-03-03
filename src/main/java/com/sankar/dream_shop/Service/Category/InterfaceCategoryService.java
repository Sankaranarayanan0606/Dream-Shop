package com.sankar.dream_shop.Service.Category;

import com.sankar.dream_shop.model.Category;

import java.util.List;

public interface InterfaceCategoryService {

    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategoryById(Long id);
}
