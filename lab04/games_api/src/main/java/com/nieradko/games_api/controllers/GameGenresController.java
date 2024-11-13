package com.nieradko.games_api.controllers;

import com.nieradko.games_api.services.GameGenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class GameGenresController {
    private final GameGenresService _service;

    @Autowired
    public GameGenresController(GameGenresService service) {
        _service = service;
    }

    @PutMapping("/genres/{uuid}")
    public ResponseEntity<Void> createGenre(@PathVariable UUID uuid) {
        try {
            _service.create(uuid);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/genres/{uuid}")
    public ResponseEntity<Void> deleteGenre(@PathVariable UUID uuid) {
        try {
            _service.delete(uuid);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
