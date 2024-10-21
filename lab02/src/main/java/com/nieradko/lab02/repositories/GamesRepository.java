package com.nieradko.lab02.repositories;

import com.nieradko.lab02.entities.Game;
import com.nieradko.lab02.entities.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GamesRepository extends JpaRepository<Game, UUID> {
    List<Game> findByGenre(GameGenre genre);
    List<Game> findByNameIgnoreCase(String name);
    List<Game> findByNameAndGenre(String name, GameGenre genre);
}
