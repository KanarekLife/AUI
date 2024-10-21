package com.nieradko.lab02.services;

import com.nieradko.lab02.entities.Game;
import com.nieradko.lab02.entities.GameGenre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GamesService {
    List<Game> findAll();
    Optional<Game> getById(UUID id);
    List<Game> findByName(String name);
    List<Game> findByGenre(GameGenre genre);
    List<Game> findByNameAndGenre(String name, GameGenre genre);
    void create(Game game);
    void update(Game game);
    void delete(Game game);
}
