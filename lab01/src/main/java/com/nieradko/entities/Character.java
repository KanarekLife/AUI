package com.nieradko.entities;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

@Builder(buildMethodName = "buildInternal")
@Value
public class Character implements Serializable {
    String name;
    int level;
    Profession profession;

    public static class CharacterBuilder {
        public Character build() {
            var character = this.buildInternal();
            character.getProfession().getCharacters().add(character);
            return character;
        }
    }
}
