package org.route.model;

import jakarta.validation.constraints.*;
import jakarta.validation.valueextraction.UnwrapByDefault;
import lombok.*;
import org.hibernate.validator.Incubating;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Brand {
    @Size(min = 2,max = 50,message = "Name must be between 2 and 50")
    @NotBlank(message = "Brand name is required")
   private String name;
    @NotBlank(message = "Brand slug is required")
    @Pattern(regexp = "^[a-z0-9-]+$",
            message = "Slug must be lowercase letters ,numbers and hyphens only")
   private  String slug;
   private Boolean active = true;
}
