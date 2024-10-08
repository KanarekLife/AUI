package com.nieradko.dtos;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CharacterDto implements Comparable<CharacterDto> {
    String name;
    int level;
    String profession;

    @Override
    public int compareTo(CharacterDto other) {
        if (this.name.compareTo(other.name) != 0) {
            return this.name.compareTo(other.name);
        } else if (this.level != other.level) {
            return this.level - other.level;
        } else {
            return this.profession.compareTo(other.profession);
        }
    }
}
