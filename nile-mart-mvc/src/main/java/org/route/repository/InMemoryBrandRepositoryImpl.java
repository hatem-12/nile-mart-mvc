package org.route.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.route.model.Brand;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InMemoryBrandRepositoryImpl implements BrandRepository {
private final List<Brand> myBrands=new ArrayList<>();

@PostConstruct
void init(){
    Brand brand1=new Brand("Samsung".trim(),"samsung".trim(),true);
    Brand brand2=new Brand("Asus".trim(),"asus".trim(),true);
    Brand brand3=new Brand("Anker".trim(),"anker".trim(),true);
    Brand brand4=new Brand("Tefal".trim(),"tefal".trim(),true);
    Brand brand5=new Brand("Old Brand".trim(),"old-brand".trim(),false);
    myBrands.add(brand1);
    myBrands.add(brand2);
    myBrands.add(brand3);
    myBrands.add(brand4);
    myBrands.add(brand5);

}

    @Override
    public List<Brand> findAllBrands() {
        return myBrands;
    }

    @Override
    public Brand findBrandBySlug(String slug) {
        for (Brand brand:myBrands){
            if (brand.getSlug().equalsIgnoreCase(slug)){
                return brand;
            }
        }
        return null;
    }

    @Override
    public void save(Brand brand) {
        myBrands.add(brand);
    }
}
