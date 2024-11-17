package com.nieradko.games_api.repositories;

import com.nieradko.games_api.entities.Game;
import com.nieradko.games_api.entities.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GamesRepository extends JpaRepository<Game, UUID> {
    List<Game> findByGenreId(UUID genreId);
    List<Game> findByNameIgnoreCase(String name);
    List<Game> findByNameAndGenre(String name, GameGenre genre);
}
