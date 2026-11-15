package org.route.service;

import lombok.RequiredArgsConstructor;
import org.route.model.Category;
import org.route.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAvailableCategory() {
        List<Category> getActiveCategory = new ArrayList<>();
        for (Category category : categoryRepository.findAllCategories()) {
            if (category.isActive()) {
                getActiveCategory.add(category);
            }
        }
        return getActiveCategory;
    }

    public List<Category> getOrderedCategory() {
        List<Category> allCategories = categoryRepository.findAllCategories();
        List<Category> result = new ArrayList<>();
        List<Category> parents = allCategories
                .stream().filter(this::isParent).sorted(Comparator.comparing(Category::getDisplayOrder)).toList();
        for (Category parent : parents) {
            result.add(parent);
            allCategories.stream().filter(category -> category.getParentSlug().equalsIgnoreCase(parent.getSlug())).sorted(Comparator.comparing(Category::getDisplayOrder)).forEach(result::add);
        }
        return result;
    }

    public Category getCategoryBySlug(String slug) {

        return categoryRepository.findCategoryBySlug(slug);
    }

    public boolean nameExist(Category category) {
        String parentSlug = Objects.toString(category.getParentSlug(), "");

        return categoryRepository.findAllCategories()
                .stream()
                .anyMatch(existing ->
                        existing.getName().equalsIgnoreCase(category.getName())
                                && Objects.equals(
                                Objects.toString(existing.getParentSlug(), ""),
                                parentSlug
                        ));
    }

    public boolean slugExist(Category category) {
        return categoryRepository.findCategoryBySlug(category.getSlug()) != null;
    }

    public boolean isParent(Category category) {
        return (category.getParentSlug() == null || category.getParentSlug().isEmpty());
    }

    public boolean validParent(Category category) {
        if (category.getParentSlug() == null ||
                category.getParentSlug().isEmpty()) {
            return true;
        }

        Category parent = getCategoryBySlug(category.getParentSlug());

        return parent != null && isParent(parent);
    }
    public boolean isSubCategory(Category category) {
        return !(isParent(category));
    }

    public List<Category> getAvailableSubCategories() {
        return getAvailableCategory().stream()
                .filter(this::isSubCategory)
                .toList();
    }

    public Map<String, String> getCategoryNameMap() {
        Map<String, String> names = new HashMap<>();

        for (Category category : categoryRepository.findAllCategories()) {
            names.put(category.getSlug(), category.getName());
        }

        return names;
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
}
