package com.nieradko.gamegenres_api.repositories;

import com.nieradko.gamegenres_api.entities.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameGenresRepository extends JpaRepository<GameGenre, UUID> {
    List<GameGenre> findByNameIgnoreCase(String name);
}
