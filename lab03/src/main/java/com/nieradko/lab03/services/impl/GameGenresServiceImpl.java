package com.nieradko.lab03.services.impl;

import com.nieradko.lab03.entities.GameGenre;
import com.nieradko.lab03.repositories.GameGenresRepository;
import com.nieradko.lab03.repositories.GamesRepository;
import com.nieradko.lab03.services.GameGenresService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class GameGenresServiceImpl implements GameGenresService {
    private final GameGenresRepository _repository;
    private final GamesRepository _gamesRepository;

    @Autowired
    public GameGenresServiceImpl(GameGenresRepository repository, GamesRepository gamesRepository) {
        _repository = repository;
        _gamesRepository = gamesRepository;
    }

    @Override
    public List<GameGenre> findAll() {
        return _repository.findAll();
    }

    @Override
    public Optional<GameGenre> getById(UUID id) {
        return _repository.findById(id);
    }

    @Override
    public List<GameGenre> findByName(String name) {
        return _repository.findByNameIgnoreCase(name);
    }

    @Override
    public void create(GameGenre gameGenre) {
        _repository.save(gameGenre);
    }

    @Override
    public void update(GameGenre gameGenre) {
        _repository.save(gameGenre);
    }

    @Override
    public void delete(GameGenre gameGenre) {
        _gamesRepository.deleteAll(_gamesRepository.findByGenre(gameGenre));

        _repository.delete(gameGenre);
    }
}
