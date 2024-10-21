package com.nieradko.lab02.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game {
    @Id
    @ToString.Exclude
    @Builder.Default
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "minimal_player_age")
    private int minimalPlayerAge;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private GameGenre genre;

    public static GameBuilder inMemoryBuilder() {
        return new InMemoryBuilder();
    }

    public static class InMemoryBuilder extends GameBuilder {
        @Override
        public Game build() {
            if (super.genre == null) {
                throw new IllegalStateException("Genre not set");
            }

            var game = super.build();
            super.genre.getGames().add(game);
            return game;
        }
    }
}
