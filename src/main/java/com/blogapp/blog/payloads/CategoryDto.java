package com.blogapp.blog.payloads;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;

    @NotBlank
    @Size(min = 4 , message = "Category title must be of min 4 chars")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10 , message = "Category description must be of min 10 chars")
    private String categoryDescription;
}
