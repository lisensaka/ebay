package com.lisen.ebay.models.request;

import com.lisen.ebay.models.response.IngredientResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeRequestDto {
    private String name;
    private String description;
    private String imagePath;
    List<IngredientResponseDto> ingredients;
}
