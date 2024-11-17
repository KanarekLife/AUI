package com.nieradko.games_api.controllers;

import com.nieradko.games_api.entities.Game;
import com.nieradko.games_api.models.CreateGameRequest;
import com.nieradko.games_api.models.GameResponse;
import com.nieradko.games_api.models.UpdateGameRequest;
import com.nieradko.games_api.services.GameGenresService;
import com.nieradko.games_api.services.GamesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class GamesController {
    private final GamesService _gamesService;
    private final GameGenresService _gameGenresService;

    public GamesController(GamesService service, GameGenresService gameGenresService) {
        _gamesService = service;
        _gameGenresService = gameGenresService;
    }

    @GetMapping("/games/")
    public ResponseEntity<List<GameResponse>> getGames() {
        return new ResponseEntity<>(_gamesService.findAll().stream().map(GameResponse::from).toList(), HttpStatus.OK);
    }

    @GetMapping("/games/{uuid}/")
    public ResponseEntity<GameResponse> getGame(@PathVariable UUID uuid) {
        var game = _gamesService.getById(uuid);

        return game.map(value -> new ResponseEntity<>(GameResponse.from(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("/genres/{uuid}/games/")
    public ResponseEntity<List<GameResponse>> getGamesFromGenre(@PathVariable UUID uuid) {
        var games = _gamesService.findByGenreId(uuid);

        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(games.stream().map(GameResponse::from).toList(), HttpStatus.OK);

    }

    @PostMapping("/genres/{uuid}/games/")
    public ResponseEntity<GameResponse> createGame(@PathVariable UUID uuid, @RequestBody CreateGameRequest req) {
        var genre = _gameGenresService.findById(uuid);

        if (genre.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var newGame = Game.builder()
                .name(req.getName())
                .minimalPlayerAge(req.getMinimalPlayerAge())
                .genre(genre.get())
                .build();

        _gamesService.create(newGame);

        return new ResponseEntity<>(GameResponse.from(newGame), HttpStatus.CREATED);
    }

    @PutMapping("/games/{uuid}/")
    public ResponseEntity<GameResponse> updateGame(@PathVariable UUID uuid, @RequestBody UpdateGameRequest req) {
        var result = _gamesService.getById(uuid);

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        var game = result.get();
        game.setName(req.getName());
        game.setMinimalPlayerAge(req.getMinimalPlayerAge());
        _gamesService.update(game);

        return new ResponseEntity<>(GameResponse.from(game), HttpStatus.OK);
    }

    @DeleteMapping("/games/{uuid}/")
    public ResponseEntity<Void> deleteGame(@PathVariable UUID uuid) {
        var game = _gamesService.getById(uuid);

        if (game.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        _gamesService.delete(game.get());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
