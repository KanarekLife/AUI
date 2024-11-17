package com.nieradko.games_api.components;

import com.nieradko.games_api.entities.Game;
import com.nieradko.games_api.entities.GameGenre;
import com.nieradko.games_api.repositories.GameGenresRepository;
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
                        // FPS
                        .id(UUID.fromString("8f577562-032f-40d4-a5a2-ce91a9212bde")),
                GameGenre.builder()
                        // Racing
                        .id(UUID.fromString("95a17e2f-0223-498a-a530-e2d626827150")),
                GameGenre.builder()
                        // RPG
                        .id(UUID.fromString("7667b1d9-6377-43f9-8b92-e2eccd041b36"))
        )
                .map(GameGenre.GameGenreBuilder::build)
                .collect(Collectors.toMap(GameGenre::getId, x -> x));

        var genres = genresByName.values();

        Stream.of(
                Game.inMemoryBuilder()
                        .id(UUID.fromString("77a44f1d-53e9-420f-ab6f-e53978a0e9e2"))
                        .name("Medal of Honour")
                        .minimalPlayerAge(21)
                        .genre(genresByName.get(UUID.fromString("8f577562-032f-40d4-a5a2-ce91a9212bde"))),
                Game.inMemoryBuilder()
                        .id(UUID.fromString("34f4616e-90f0-49ce-87bf-95589aa1874a"))
                        .name("Gran Turismo")
                        .minimalPlayerAge(15)
                        .genre(genresByName.get(UUID.fromString("95a17e2f-0223-498a-a530-e2d626827150"))),
                Game.inMemoryBuilder()
                        .id(UUID.fromString("868f051d-1478-4ed0-a7e1-c151b6b45964"))
                        .name("Disco Elysium")
                        .minimalPlayerAge(18)
                        .genre(genresByName.get(UUID.fromString("7667b1d9-6377-43f9-8b92-e2eccd041b36"))),
                Game.inMemoryBuilder()
                        .id(UUID.fromString("04a6af4b-f070-4755-8626-d182889f72b5"))
                        .name("Pokemon")
                        .minimalPlayerAge(7)
                        .genre(genresByName.get(UUID.fromString("7667b1d9-6377-43f9-8b92-e2eccd041b36")))
        ).forEach(Game.GameBuilder::build);

        _repository.saveAllAndFlush(genres);
        System.out.println("Database seeded");
    }
}
