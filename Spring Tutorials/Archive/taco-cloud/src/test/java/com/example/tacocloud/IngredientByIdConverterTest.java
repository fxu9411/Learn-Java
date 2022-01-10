package com.example.tacocloud;

import static org.assertj.core.api.Assertions.assertThat;
import com.example.tacocloud.Controller.IngredientByIdConverter;
import com.example.tacocloud.Data.IngredientRepository;
import com.example.tacocloud.Domain.Ingredient;
import com.example.tacocloud.Domain.Ingredient.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IngredientByIdConverterTest {

    private IngredientByIdConverter converter;

    @BeforeEach
    public void setup() {
        IngredientRepository ingredientRepo = mock(IngredientRepository.class);
        when(ingredientRepo.findById("AAAA"))
                .thenReturn(Optional.of(new Ingredient("AAAA", "TEST INGREDIENT", Type.CHEESE)));
        when(ingredientRepo.findById("ZZZZ"))
                .thenReturn(Optional.empty());

        this.converter = new IngredientByIdConverter(ingredientRepo);
    }

    @Test
    public void shouldReturnValueWhenPresent() {
        assertThat(converter.convert("AAAA"))
                .isEqualTo(new Ingredient("AAAA", "TEST INGREDIENT", Type.CHEESE));
    }

    @Test
    public void shouldReturnNullWhenMissing() {
        assertThat(converter.convert("ZZZZ")).isNull();
    }

}
