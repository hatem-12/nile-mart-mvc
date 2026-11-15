package org.route.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @NotBlank(message = "SKU can't be empty.")
    private String sku;
    @NotBlank(message = "Product name can't be empty")
    private String name;
    @NotBlank(message = "Category can't be empty")
    private String category;
    @NotNull(message = "Price is required")
    @Positive(message = "price must be greater than zero.")
    private BigDecimal price;
    @NotNull(message = "inStock is required")
    @PositiveOrZero(message = "in stock must be positive or zero")
    private Integer inStock;
    private String brandSlug;
}
