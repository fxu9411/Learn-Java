package com.example.tacocloud.Domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access=AccessLevel.PUBLIC, force=true)
public class Ingredient {

    @Id
    private final String id;

    private final String name;

    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}