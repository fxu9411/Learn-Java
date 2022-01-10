package com.example.tacocloud.Controller;


import com.example.tacocloud.Data.IngredientRepository;
import com.example.tacocloud.Data.TacoRepository;
import com.example.tacocloud.Data.UserRepository;
import com.example.tacocloud.Domain.Ingredient;
import com.example.tacocloud.Domain.Ingredient.Type;
import com.example.tacocloud.Domain.Taco;
import com.example.tacocloud.Domain.TacoOrder;
import com.example.tacocloud.Domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;

    private final TacoRepository tacoRepository;

    private final UserRepository userRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository, UserRepository userRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();

        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();

        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "order")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute(name = "user")
    public User user(Principal principal) {
        String username = principal.getName();
        return userRepository.findByUsername(username);
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Processing Taco: " + taco);
        Taco saved = tacoRepository.save(taco);
        tacoOrder.addTaco(saved);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
