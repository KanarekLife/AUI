package com.nieradko.entities;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class GameGenre implements Serializable, Comparable<GameGenre> {
    String name;
    boolean supportsMultiplayer;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Builder.Default
    List<Game> games = new ArrayList<>();

    @Override
    public int compareTo(GameGenre o) {
        if (name.compareTo(o.getName()) != 0) {
            return name.compareTo(o.getName());
        } else if (supportsMultiplayer != o.isSupportsMultiplayer()) {
            return -1;
        } else {
            return 0;
        }
    }
}
