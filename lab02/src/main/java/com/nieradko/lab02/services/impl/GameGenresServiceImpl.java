package com.nieradko.lab02.services.impl;

import com.nieradko.lab02.entities.GameGenre;
import com.nieradko.lab02.repositories.GameGenresRepository;
import com.nieradko.lab02.services.GameGenresService;
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

    @Autowired
    public GameGenresServiceImpl(GameGenresRepository repository) {
        _repository = repository;
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
    public void add(GameGenre gameGenre) {
        _repository.save(gameGenre);
    }

    @Override
    public void update(GameGenre gameGenre) {
        _repository.save(gameGenre);
    }

    @Override
    public void delete(GameGenre gameGenre) {
        _repository.delete(gameGenre);
    }
}
