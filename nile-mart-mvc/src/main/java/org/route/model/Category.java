  package org.route.model;

  import jakarta.validation.constraints.*;
  import lombok.*;

  @AllArgsConstructor
  @Data
  @NoArgsConstructor
  @Builder
  public class Category {
    @Size(min = 2,max = 50,message = "Name must be between 2 and 50")
    @NotBlank(message = "Category name is required")
    private String  name;
    @NotBlank(message = "Category slug is required")
    @Pattern(regexp = "^[a-z0-9-]+$",
            message = "Slug must be lowercase letters ,numbers and hyphens only")
    private  String slug;
    @Builder.Default
    private String parentSlug= "";
    @NotNull(message = "Display order is required")
    @PositiveOrZero(message = "Display order must be zero or greater")
    private Integer displayOrder;
    @Builder.Default
    private boolean active = true;
  }
