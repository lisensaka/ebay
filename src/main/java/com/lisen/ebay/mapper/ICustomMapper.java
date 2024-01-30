package com.lisen.ebay.mapper;

import com.lisen.ebay.models.Ingredient;
import com.lisen.ebay.models.Recipe;
import com.lisen.ebay.models.User;
import com.lisen.ebay.models.request.RecipeRequestDto;
import com.lisen.ebay.models.request.SingUpModel;
import com.lisen.ebay.models.response.IngredientResponseDto;
import com.lisen.ebay.models.response.RecipeResponseDto;
import com.lisen.ebay.models.response.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ICustomMapper {
//    @Mappings({
//            @Mapping(source = "name", target = "name"),
//            @Mapping(source = "description", target = "description"),
//            @Mapping(source = "imagePath", target = "imagePath")
//    }
//    )
    RecipeResponseDto convertRecipeToRecipeResponseDto(Recipe recipe);
    IngredientResponseDto convertListIngredientToListIngredientResponseDto(Ingredient ingredients);
    Recipe convertRecipeRequestDtoToRecipe(RecipeRequestDto recipeRequestDto);
    Ingredient convertIngredientRequestDtoToIngredient(IngredientResponseDto ingredientResponseDto);
//    @Mappings({
//            @Mapping(source = "fullName", target = "fullName"),
//            @Mapping(source = "email", target = "email"),
//            @Mapping(source = "password", target = "password"),
//            @Mapping(source = "role", target = "role")
//    }
//    )
    User convertSignUpModelToUser(SingUpModel singUpModel);

//    @Mappings({
//            @Mapping(source = "fullName", target = "fullName"),
//            @Mapping(source = "email", target = "email"),
//            @Mapping(source = "password", target = "password"),
//            @Mapping(source = "role", target = "role")
//    }
//    )
    UserResponseDto convertUserToUserResponseDto(User user);
}
