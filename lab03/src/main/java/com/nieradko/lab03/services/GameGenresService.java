package com.nieradko.lab03.services;

import com.nieradko.lab03.entities.GameGenre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameGenresService {
    List<GameGenre> findAll();
    Optional<GameGenre> getById(UUID id);
    List<GameGenre> findByName(String name);
    void create(GameGenre gameGenre);
    void update(GameGenre gameGenre);
    void delete(GameGenre gameGenre);
}
