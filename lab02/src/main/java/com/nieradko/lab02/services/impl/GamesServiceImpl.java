package com.nieradko.lab02.services.impl;

import com.nieradko.lab02.entities.Game;
import com.nieradko.lab02.entities.GameGenre;
import com.nieradko.lab02.repositories.GamesRepository;
import com.nieradko.lab02.services.GamesService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class GamesServiceImpl implements GamesService {
    private final GamesRepository _repository;

    @Autowired
    public GamesServiceImpl(GamesRepository repository) {
        _repository = repository;
    }

    @Override
    public List<Game> findAll() {
        return _repository.findAll();
    }

    @Override
    public Optional<Game> getById(UUID id) {
        return _repository.findById(id);
    }

    @Override
    public List<Game> findByName(String name) {
        return _repository.findByNameIgnoreCase(name);
    }

    @Override
    public List<Game> findByGenre(GameGenre genre) {
        return _repository.findByGenre(genre);
    }

    @Override
    public List<Game> findByNameAndGenre(String name, GameGenre genre) {
        return _repository.findByNameAndGenre(name, genre);
    }

    @Override
    public void create(Game game) {
        _repository.save(game);
    }

    @Override
    public void update(Game game) {
        _repository.save(game);
    }

    @Override
    public void delete(Game game) {
        _repository.delete(game);
    }
}
