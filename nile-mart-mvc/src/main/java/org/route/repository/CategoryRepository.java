package org.route.repository;

import org.route.model.Category;

import java.util.ArrayList;
import java.util.List;

public interface CategoryRepository {
    List<Category> findAllCategories();
    Category findCategoryBySlug(String slug);
    void save(Category category);
}
