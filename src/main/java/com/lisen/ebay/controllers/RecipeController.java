package com.lisen.ebay.controllers;

import com.lisen.ebay.models.request.RecipeRequestDto;
import com.lisen.ebay.models.response.RecipeResponseDto;
import com.lisen.ebay.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("${app.api.allowed.origin}")
//@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public RecipeResponseDto addNewRecipe(@RequestBody RecipeRequestDto recipeRequestDto){
        return recipeService.addNewRecipe(recipeRequestDto);
    }

    @GetMapping("/all")
    public List<RecipeResponseDto> getAllRecipeResponseDto(){
        return recipeService.getRecipeResponseDtoList();
    }

    @GetMapping("/by-id/{recipeId}")
    public RecipeResponseDto getRecipeById(@PathVariable long recipeId){
        return recipeService.getRecipeResponseDtoById(recipeId);
//        return String.format("Recipe with id: %s, was deleted successfully!", recipeId);
    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable long recipeId){
        recipeService.deleteRecipe(recipeId);
        return String.format("Recipe with id: %s, was deleted successfully!", recipeId);
    }
}
