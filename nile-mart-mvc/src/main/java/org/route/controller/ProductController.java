package org.route.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.route.model.Category;
import org.route.model.Product;
import org.route.service.BrandService;
import org.route.service.CategoryService;
import org.route.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
private final BrandService brandService;
private final CategoryService categoryService;

    private void addFormData(Model model) {
        model.addAttribute("categories", productService.getSubcategory());
        model.addAttribute("brands", productService.getBrands());
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("availableProducts", productService.getAvailableProducts());
        model.addAttribute(
                "categoryNames",
                categoryService.getCategoryNameMap()
        );

        model.addAttribute(
                "brandNames",
                brandService.getBrandNameMap()
        );
        return "products";
    }

    @GetMapping("/products/new")
    public String showForm(Model model) {
        model.addAttribute("product", new Product());
        addFormData(model);
        return "productForm";
    }

    @PostMapping("/products")
    public String addProduct(@ModelAttribute("product") @Valid Product product,
                             BindingResult result,Model model) {
        if (productService.isProductExist(product.getSku())) {
            result.rejectValue("sku", "400", "sku is already exist");
        }
        if (productService.isValidBrand(product.getBrandSlug())){
            result.rejectValue("brandSlug","invalid","Choose an existing active brand");
        }

        if (productService.isValidCategory(product.getCategory())) {
            result.rejectValue("category", "invalid", "Choose a valid subcategory");
        }

        if (result.hasErrors()) {
            addFormData(model);
            return "productForm";
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }
}
