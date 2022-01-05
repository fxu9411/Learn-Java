package com.example.tacocloud.Data;

import com.example.tacocloud.Domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
