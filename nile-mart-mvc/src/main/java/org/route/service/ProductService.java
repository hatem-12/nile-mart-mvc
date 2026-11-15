package org.route.service;


import lombok.RequiredArgsConstructor;
import org.route.model.Brand;
import org.route.model.Category;
import org.route.model.Product;
import org.route.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final BrandService brandService;

    public List<Product> getAvailableProducts() {
        List<Product> availableProducts = new ArrayList<>();
        for (Product p : productRepository.findAll()) {
            if (p.getInStock() > 0)
                availableProducts.add(p);
        }
        return availableProducts;
    }
    public boolean isValidBrand(String slug) {
        if (slug == null || slug.isBlank()) {
            return false;
        }

        Brand brand = brandService.getBrandBySlug(slug);

        return brand == null || !brand.getActive();
    }

    public List<Category> getSubcategory(){
        return categoryService.getAvailableSubCategories();
    }
    public List<Brand> getBrands(){
        return brandService.getAvailableBrands();
    }
    public boolean isValidCategory(String slug) {
        Category category = categoryService.getCategoryBySlug(slug);

        return category == null || !categoryService.isSubCategory(category);
    }
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public boolean isProductExist(String sku) {
        return productRepository.findBySku(sku) != null;
    }
}
