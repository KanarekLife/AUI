package com.nieradko.entities;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

@Builder(buildMethodName = "buildInternal")
@Data
public class Game implements Serializable, Comparable<Game> {
    String name;
    int minimalPlayerAge;
    GameGenre genre;

    @Override
    public int compareTo(Game o) {
        if (name.compareTo(o.name) != 0) {
            return name.compareTo(o.name);
        }else if (minimalPlayerAge != o.minimalPlayerAge) {
            return Integer.compare(minimalPlayerAge, o.minimalPlayerAge);
        } else {
            return genre.compareTo(o.genre);
        }
    }

    public static class GameBuilder {
        public Game build() {
            var game = this.buildInternal();
            game.getGenre().getGames().add(game);
            return game;
        }
    }
}
