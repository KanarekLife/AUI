package com.nieradko.gamegenres_api.services.impl;

import com.nieradko.gamegenres_api.entities.GameGenre;
import com.nieradko.gamegenres_api.repositories.GameGenresRepository;
import com.nieradko.gamegenres_api.services.GameGenresService;
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
    @Qualifier("gamesAPI")
    private final RestTemplate _gamesAPI;

    @Autowired
    public GameGenresServiceImpl(GameGenresRepository repository, RestTemplate gamesAPI) {
        _repository = repository;
        _gamesAPI = gamesAPI;
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
        _gamesAPI.put("/genres/" + gameGenre.getId(), null);

        _repository.save(gameGenre);
    }

    @Override
    public void update(GameGenre gameGenre) {
        _repository.save(gameGenre);
    }

    @Override
    public void delete(GameGenre gameGenre) {
        _gamesAPI.delete("/genres/" + gameGenre.getId());

        _repository.delete(gameGenre);
    }
}
