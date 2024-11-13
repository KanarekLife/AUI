package com.nieradko.games_api.models;

import com.nieradko.games_api.entities.Game;
import lombok.Value;

import java.util.UUID;

@Value
public class GameResponse {
    UUID id;
    String name;
    int minimalPlayerAge;
    UUID genreId;

    public static GameResponse from(Game game) {
        return new GameResponse(
                game.getId(),
                game.getName(),
                game.getMinimalPlayerAge(),
                game.getGenre().getId()
        );
    }
}
