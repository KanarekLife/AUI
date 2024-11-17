package com.nieradko.games_api.services;

import com.nieradko.games_api.entities.GameGenre;

import java.util.Optional;
import java.util.UUID;

public interface GameGenresService {
    void create(UUID id);
    void delete(UUID id);
    Optional<GameGenre> findById(UUID id);
}
