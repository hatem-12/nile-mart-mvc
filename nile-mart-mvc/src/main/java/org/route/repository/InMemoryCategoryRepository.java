package org.route.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.route.model.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InMemoryCategoryRepository implements CategoryRepository{
    private final List<Category> myCategory = new ArrayList<>();

    @PostConstruct
    void init() {
        Category category1 = Category.builder()
                .name("Electronics")
                .slug("electronics")
                .parentSlug("")
                .displayOrder(1)
                .active(true)
                .build();

        Category category2 = Category.builder()
                .name("Mobile Phones")
                .slug("mobile-phones")
                .parentSlug("electronics")
                .displayOrder(1)
                .active(true)
                .build();

        Category category3 = Category.builder()
                .name("Laptops")
                .slug("laptops")
                .parentSlug("electronics")
                .displayOrder(2)
                .active(true)
                .build();

        Category category4 = Category.builder()
                .name("Accessories")
                .slug("electronics-accessories")
                .parentSlug("electronics")
                .displayOrder(3)
                .active(true)
                .build();

        Category category5 = Category.builder()
                .name("Home & Kitchen")
                .slug("home-kitchen")
                .parentSlug("")
                .displayOrder(2)
                .active(true)
                .build();

        Category category6 = Category.builder()
                .name("Cookware")
                .slug("cookware")
                .parentSlug("home-kitchen")
                .displayOrder(1)
                .active(true)
                .build();

        Category category7 = Category.builder()
                .name("Lighting")
                .slug("lighting")
                .parentSlug("home-kitchen")
                .displayOrder(2)
                .active(true)
                .build();

        Category category8 = Category.builder()
                .name("Fashion")
                .slug("fashion")
                .parentSlug("")
                .displayOrder(3)
                .active(false)
                .build();

        Category category9 = Category.builder()
                .name("Men’s Clothing")
                .slug("mens-clothing")
                .parentSlug("fashion")
                .displayOrder(1)
                .active(true)
                .build();
        myCategory.add(category1);
        myCategory.add(category2);
        myCategory.add(category3);
        myCategory.add(category4);
        myCategory.add(category5);
        myCategory.add(category6);
        myCategory.add(category7);
        myCategory.add(category8);
        myCategory.add(category9);

    }

    @Override
    public List<Category> findAllCategories() {
        return myCategory;
    }

    @Override
    public Category findCategoryBySlug(String slug) {
        for (Category category:myCategory){
            if (category.getSlug().equalsIgnoreCase(slug)){
                return category;
            }
        }
        return null;
    }

    @Override
    public void save(Category category) {
myCategory.add(category);
    }
}
