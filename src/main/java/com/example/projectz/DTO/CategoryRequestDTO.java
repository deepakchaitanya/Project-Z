package com.example.projectz.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDTO {
    private String title;
    private CategoryRequestDTO category;
    private String description;
    private String imageURL;
}
