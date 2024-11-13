package com.nieradko.games_api.services;

import com.nieradko.games_api.entities.Game;
import com.nieradko.games_api.entities.GameGenre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GamesService {
    List<Game> findAll();
    Optional<Game> getById(UUID id);
    List<Game> findByName(String name);
    List<Game> findByGenreId(UUID genreId);
    List<Game> findByNameAndGenre(String name, GameGenre genre);
    void create(Game game);
    void update(Game game);
    void delete(Game game);
}
