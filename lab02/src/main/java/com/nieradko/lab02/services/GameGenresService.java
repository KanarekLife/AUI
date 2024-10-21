package com.nieradko.lab02.services;

import com.nieradko.lab02.entities.GameGenre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameGenresService {
    List<GameGenre> findAll();
    Optional<GameGenre> getById(UUID id);
    List<GameGenre> findByName(String name);
    void add(GameGenre gameGenre);
    void update(GameGenre gameGenre);
    void delete(GameGenre gameGenre);
}
