package com.nieradko.gamegenres_api.models;

import com.nieradko.gamegenres_api.entities.GameGenre;
import lombok.Value;

import java.util.UUID;

@Value
public class GameGenreResponse {
    UUID id;
    String name;
    boolean supportsMultiplayer;

    public static GameGenreResponse from(GameGenre genre) {
        return new GameGenreResponse(genre.getId(), genre.getName(), genre.isSupportsMultiplayer());
    }
}
