package com.nieradko.dtos;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class GameDto implements Comparable<GameDto> {
    String name;
    int minimalPlayerAge;
    String genre;
    boolean supportsMultiplayer;

    @Override
    public int compareTo(GameDto other) {
        if (this.name.compareTo(other.name) != 0) {
            return this.name.compareTo(other.name);
        } else if (this.minimalPlayerAge != other.minimalPlayerAge) {
            return this.minimalPlayerAge - other.minimalPlayerAge;
        } else {
            return this.genre.compareTo(other.genre);
        }
    }
}
