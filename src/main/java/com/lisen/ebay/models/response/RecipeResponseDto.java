package com.lisen.ebay.models.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeResponseDto {
    private long id;
    private String name;
    private String description;
    private String imagePath;
    List<IngredientResponseDto> ingredients;
}
