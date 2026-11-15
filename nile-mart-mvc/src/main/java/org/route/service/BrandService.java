package org.route.service;


import lombok.RequiredArgsConstructor;
import org.route.model.Brand;
import org.route.repository.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor

public class BrandService {
    private final BrandRepository brandRepository;
    public List<Brand> getBrands(){
        return brandRepository.findAllBrands();
    }
    public List<Brand> getAvailableBrands(){
        List<Brand> availableBrands=new ArrayList<>();
        for (Brand brand:brandRepository.findAllBrands()){
            if (brand.getActive()){
                availableBrands.add(brand);
            }
        }
        return availableBrands;
    }
    public Brand getBrandBySlug(String slug){
      return brandRepository.findBrandBySlug(slug);
    }
    public boolean nameExists(String name) {
        return brandRepository.findAllBrands()
                .stream()
                .anyMatch(brand ->
                        brand.getName().equalsIgnoreCase(name));
    }
    public void saveBrand(Brand brand){
      if (brand==null){
          throw new RuntimeException("Brand can't be null");
      }
      if (brand.getActive()==null){
          brand.setActive(true);
      }
      brandRepository.save(brand);
    }
    public Map<String, String> getBrandNameMap() {
        Map<String, String> names = new HashMap<>();

        for (Brand brand : brandRepository.findAllBrands()) {
            names.put(brand.getSlug(), brand.getName());
        }
        return names;
    }
}
