package com.example.tacocloud;

import com.example.tacocloud.Domain.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class TacoCloudClient {

    RestTemplate restTemplate = new RestTemplate();

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate.getForObject("http://localhost:8080/data-api/ingredients/{id}",
                Ingredient.class, ingredientId);
    }

    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put("http://localhost:8080/ingredients/{id}",
                ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        restTemplate.delete(
                "http://localhost:8080/ingredients/{id}",
                ingredient.getId()
        );
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return restTemplate.postForObject(
                "http://localhost:8080/ingredients",
                ingredient,
                Ingredient.class
        );
    }
}
