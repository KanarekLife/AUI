package com.nieradko.games_api.services.impl;

import com.nieradko.games_api.entities.Game;
import com.nieradko.games_api.entities.GameGenre;
import com.nieradko.games_api.repositories.GamesRepository;
import com.nieradko.games_api.services.GamesService;
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
    public List<Game> findByGenreId(UUID genreId) {
        return _repository.findByGenreId(genreId);
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
