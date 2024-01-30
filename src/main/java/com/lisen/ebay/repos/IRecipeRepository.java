package com.lisen.ebay.repos;

import com.lisen.ebay.models.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecipeRepository extends CrudRepository<Recipe, Long> {
}
