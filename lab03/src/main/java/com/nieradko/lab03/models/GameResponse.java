package com.nieradko.lab03.models;

import com.nieradko.lab03.entities.Game;
import lombok.Value;

import java.util.UUID;

@Value
public class GameResponse {
    UUID id;
    String name;
    int minimalPlayerAge;
    GameGenreInGameResponse genre;

    @Value
    public static class GameGenreInGameResponse {
        UUID id;
        String name;
    }

    public static GameResponse from(Game game) {
        return new GameResponse(
                game.getId(),
                game.getName(),
                game.getMinimalPlayerAge(),
                new GameGenreInGameResponse(game.getGenre().getId(), game.getGenre().getName())
        );
    }
}
