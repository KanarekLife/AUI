package com.nieradko.games_api.services.impl;

import com.nieradko.games_api.entities.GameGenre;
import com.nieradko.games_api.repositories.GameGenresRepository;
import com.nieradko.games_api.repositories.GamesRepository;
import com.nieradko.games_api.services.GameGenresService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public void create(UUID id) {
        if (_repository.findById(id).isPresent()) {
            throw new IllegalStateException("Game genre with id " + id + " already exists");
        }

        _repository.save(GameGenre.builder().id(id).build());
    }

    @Override
    public void delete(UUID id) {
        var result = _repository.findById(id);

        if (result.isEmpty()) {
            throw new IllegalStateException("Game genre with id " + id + " does not exist");
        }

        _gamesRepository.deleteAll(_gamesRepository.findByGenreId(id));
        _repository.delete(result.get());
    }

    @Override
    public Optional<GameGenre> findById(UUID id) {
        return _repository.findById(id);
    }
}
