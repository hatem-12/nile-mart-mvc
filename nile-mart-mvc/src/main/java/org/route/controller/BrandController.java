package org.route.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.route.model.Brand;
import org.route.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BrandController {
private final BrandService brandService;
@GetMapping("/brands")
    public String availableBrands(Model model){
    model.addAttribute("brands",brandService.getBrands());
    return "brands";
}
@GetMapping("/brands/new")
public String ShowBrandsForm(Model model){
    model.addAttribute("brands",new Brand());
    return "newBrand";
}
@PostMapping("/brands")
    public String saveBrand( @Valid @ModelAttribute("brands") Brand brand, BindingResult result){
    if (brandService.getBrandBySlug(brand.getSlug()) != null) {
        result.rejectValue("slug", "duplicate",
                "This Brand Slug already exists");
    }


    if (brandService.nameExists(brand.getName())) {
        result.rejectValue("name", "duplicate",
                "This Brand Name already exists");
    }
if (result.hasErrors()){
    return "newBrand";
}
brandService.saveBrand(brand);
    return "redirect:/brands";
}
}
