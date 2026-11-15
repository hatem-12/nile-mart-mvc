package org.route.repository;

import org.route.model.Brand;

import java.util.List;

public interface BrandRepository {
List<Brand> findAllBrands();
Brand findBrandBySlug(String slug);
void save(Brand brand);
}
