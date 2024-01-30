package com.lisen.ebay.models.request;

import com.lisen.ebay.mapper.ICustomMapper;
import com.lisen.ebay.models.Recipe;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
public class RecipeRequestDtoToRecipe {
    private final static ICustomMapper customMapper = Mappers.getMapper(ICustomMapper.class);

    public Recipe convertRecipeRequestDtoToRecipe(RecipeRequestDto recipeRequestDto){
        Recipe recipe = customMapper.convertRecipeRequestDtoToRecipe(recipeRequestDto);
        recipe.getIngredients().forEach(ingredient -> ingredient.setRecipe(recipe));
        return recipe;
    }
}
