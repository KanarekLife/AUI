package com.nieradko.lab03.repositories;

import com.nieradko.lab03.entities.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameGenresRepository extends JpaRepository<GameGenre, UUID> {
    List<GameGenre> findByNameIgnoreCase(String name);
}
