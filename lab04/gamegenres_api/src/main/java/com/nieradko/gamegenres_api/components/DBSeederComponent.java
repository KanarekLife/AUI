package com.nieradko.gamegenres_api.components;

import com.nieradko.gamegenres_api.entities.GameGenre;
import com.nieradko.gamegenres_api.repositories.GameGenresRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DBSeederComponent {
    private final GameGenresRepository _repository;

    @Autowired
    public DBSeederComponent(GameGenresRepository repository) {
        _repository = repository;
    }

    @PostConstruct
    public void seed() {
        if (_repository.count() > 0) {
            return;
        }

        var genresByName = Stream.of(
                GameGenre.builder()
                        .id(UUID.fromString("8f577562-032f-40d4-a5a2-ce91a9212bde"))
                        .name("FPS")
                        .supportsMultiplayer(true),
                GameGenre.builder()
                        .id(UUID.fromString("95a17e2f-0223-498a-a530-e2d626827150"))
                        .name("Racing")
                        .supportsMultiplayer(true),
                GameGenre.builder()
                        .id(UUID.fromString("7667b1d9-6377-43f9-8b92-e2eccd041b36"))
                        .name("RPG")
                        .supportsMultiplayer(false)
        )
                .map(GameGenre.GameGenreBuilder::build)
                .collect(Collectors.toMap(GameGenre::getName, x -> x));

        var genres = genresByName.values();

        _repository.saveAllAndFlush(genres);

        System.out.println("Database seeded");
    }
}
