package org.route.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.route.model.Category;
import org.route.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categories")
    public String displayCategories(Model model) {
        model.addAttribute("categories", categoryService.getOrderedCategory());
        model.addAttribute("categoryNames",
                categoryService.getCategoryNameMap());
        return "categories";
    }

    private void addParentCategories(Model model) {
        model.addAttribute("parentCategories",
                categoryService.getAvailableCategory()
                        .stream()
                        .filter(categoryService::isParent)
                        .toList());
    }
    @GetMapping("/categories/new")
    public String showCategoryForm(Model model) {
        model.addAttribute("categories", new Category());
        addParentCategories(model);
        return "categoryForm";
    }
    @PostMapping("/categories")
    public String saveCategory(@Valid @ModelAttribute("categories") Category category, BindingResult result,Model model) {
        if (categoryService.slugExist(category))
            result.rejectValue("slug", "duplicated", "Slug is already exist");

        if (!categoryService.validParent(category))
            result.rejectValue(
                    "parentSlug",
                    "error",
                    "Parent must be a valid top-level category"
            );

        if (categoryService.nameExist(category)) {
            result.rejectValue("name", "duplicated", "Name is already exist");
        }
        if (result.hasErrors()) {
            addParentCategories(model);
            return "categoryForm";
        }
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }
}
