package com.lisen.ebay.services;

import com.lisen.ebay.mapper.ICustomMapper;
import com.lisen.ebay.models.Recipe;
import com.lisen.ebay.models.request.RecipeRequestDto;
import com.lisen.ebay.models.request.RecipeRequestDtoToRecipe;
import com.lisen.ebay.models.response.IngredientResponseDto;
import com.lisen.ebay.models.response.RecipeResponseDto;
import com.lisen.ebay.repos.IRecipeRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final IRecipeRepository iRecipeRepository;
    private final static ICustomMapper customMapper = Mappers.getMapper(ICustomMapper.class);
    private final RecipeRequestDtoToRecipe recipeRequestDtoConverter;

    public List<RecipeResponseDto> getRecipeResponseDtoList() {
        List<Recipe> recipes = (List<Recipe>) iRecipeRepository.findAll();
        return recipes.stream()
                .map(recipe -> {
                    RecipeResponseDto recipeResponseDto = customMapper.convertRecipeToRecipeResponseDto(recipe);
                    List<IngredientResponseDto> ingredientResponseDtos = convertListIngredientsToListIngredientResponseDtos(recipe);
                    recipeResponseDto.setIngredients(ingredientResponseDtos);
                    return recipeResponseDto;
                })
                .collect(Collectors.toList());
    }

    private static List<IngredientResponseDto> convertListIngredientsToListIngredientResponseDtos(Recipe recipe) {
        return recipe.getIngredients().stream().map(
                customMapper::convertListIngredientToListIngredientResponseDto
        ).collect(Collectors.toList());
//        return customMapper.convertListIngredientToListIngredientResponseDto(recipe.getIngredients());
    }

    public void deleteRecipe(long recipeId) {
        Recipe recipe = getRecipeById(recipeId);
        iRecipeRepository.delete(recipe);
    }

    private Recipe getRecipeById(long recipeId) {
        return iRecipeRepository.findById(recipeId).orElseThrow(() -> new NoSuchElementException(String.format("Recipe with id: %s, does not exist!", recipeId)));
    }

    public RecipeResponseDto getRecipeResponseDtoById(long recipeId) {
        return customMapper.convertRecipeToRecipeResponseDto(getRecipeById(recipeId));
    }

    public RecipeResponseDto addNewRecipe(RecipeRequestDto recipeRequestDto) {
        Recipe recipe = recipeRequestDtoConverter.convertRecipeRequestDtoToRecipe(recipeRequestDto);
        return customMapper.convertRecipeToRecipeResponseDto(iRecipeRepository.save(recipe));
    }
}
