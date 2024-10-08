package com.nieradko.entities;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Value
public class Profession implements Serializable {
    String name;
    int baseArmor;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    List<Character> characters = new ArrayList<>();
}
