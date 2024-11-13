package com.nieradko.gamegenres_api.controllers;

import com.nieradko.gamegenres_api.entities.GameGenre;
import com.nieradko.gamegenres_api.models.CreateGenreRequest;
import com.nieradko.gamegenres_api.models.GameGenreResponse;
import com.nieradko.gamegenres_api.models.UpdateGenreRequest;
import com.nieradko.gamegenres_api.services.GameGenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class GameGenresController {
    private final GameGenresService _service;

    @Autowired
    public GameGenresController(GameGenresService service) {
        _service = service;
    }

    @GetMapping("/genres/")
    public ResponseEntity<List<GameGenreResponse>> getAllGenres() {
        var genres = _service.findAll();

        return new ResponseEntity<>(genres.stream().map(GameGenreResponse::from).toList(), HttpStatus.OK);
    }

    @GetMapping("/genres/{uuid}/")
    public ResponseEntity<GameGenreResponse> getGenre(@PathVariable UUID uuid) {
        var genre = _service.getById(uuid);

        return genre.map(gameGenre -> new ResponseEntity<>(GameGenreResponse.from(gameGenre), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/genres/")
    public ResponseEntity<GameGenreResponse> createGenre(@RequestBody CreateGenreRequest req) {
        var genre = GameGenre.builder()
                .name(req.getName())
                .supportsMultiplayer(req.isSupportsMultiplayer())
                .build();

        _service.create(genre);

        return new ResponseEntity<>(GameGenreResponse.from(genre), HttpStatus.CREATED);
    }

    @PutMapping("/genres/{uuid}/")
    public ResponseEntity<GameGenreResponse> updateGenre(@PathVariable UUID uuid, @RequestBody UpdateGenreRequest req) {
        var result = _service.getById(uuid);

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var genre = result.get();
        genre.setName(req.getName());
        genre.setSupportsMultiplayer(req.isSupportsMultiplayer());
        _service.update(genre);

        return new ResponseEntity<>(GameGenreResponse.from(genre), HttpStatus.OK);
    }

    @DeleteMapping("/genres/{uuid}/")
    public ResponseEntity<Void> deleteGenre(@PathVariable UUID uuid) {
        var genre = _service.getById(uuid);

        if (genre.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        _service.delete(genre.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
