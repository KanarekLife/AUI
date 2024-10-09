package com.nieradko;

import com.nieradko.dtos.GameDto;
import com.nieradko.entities.Game;
import com.nieradko.entities.GameGenre;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 2\n");
        var gameGenresByName = Stream.of(
                GameGenre.builder()
                        .name("FPS")
                        .supportsMultiplayer(true)
                        .build(),
                GameGenre.builder()
                        .name("Racing")
                        .supportsMultiplayer(true)
                        .build(),
                GameGenre.builder()
                        .name("RPG")
                        .supportsMultiplayer(false)
                        .build()
        ).collect(Collectors.toMap(GameGenre::getName, x -> x));
        var allGameGenres = gameGenresByName.values().stream().toList();

        var games = List.of(
                Game.builder()
                        .name("Medal of Honour")
                        .minimalPlayerAge(21)
                        .genre(gameGenresByName.get("FPS"))
                        .build(),
                Game.builder()
                        .name("Gran Turismo")
                        .minimalPlayerAge(15)
                        .genre(gameGenresByName.get("Racing"))
                        .build(),
                Game.builder()
                        .name("Disco Elysium")
                        .minimalPlayerAge(18)
                        .genre(gameGenresByName.get("RPG"))
                        .build(),
                Game.builder()
                        .name("Pokemon")
                        .minimalPlayerAge(7)
                        .genre(gameGenresByName.get("RPG"))
                        .build()
        );

        allGameGenres.forEach(gameGenre -> {
            System.out.printf("%s:\n", gameGenre.getName());
            gameGenre.getGames().forEach(game -> System.out.printf("\t%s\n", game.toString()));
        });

        System.out.println("\nTask 3\n");
        var allGames = allGameGenres.stream()
                .flatMap(x -> x.getGames().stream())
                .collect(Collectors.toSet());
        allGames.forEach(System.out::println);

        System.out.println("\nTask 4\n");
        allGames.stream()
                .filter(x -> x.getMinimalPlayerAge() > 15)
                .sorted(Comparator.comparing(x -> x.getGenre().getName()))
                .forEach(System.out::println);

        System.out.println("\nTask 5\n");
        var allGameDtos = allGames.stream()
                .map(x -> GameDto.builder()
                        .name(x.getName())
                        .minimalPlayerAge(x.getMinimalPlayerAge())
                        .genre(x.getGenre().getName())
                        .supportsMultiplayer(x.getGenre().isSupportsMultiplayer())
                        .build()
                )
                .sorted()
                .toList();
        allGameDtos.forEach(System.out::println);

        System.out.println("\nTask 6\n");
        // Save to file
        try (
                var fs = new FileOutputStream("data.bin");
                var os = new ObjectOutputStream(fs);
        ) {
            os.writeObject(allGameGenres);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Read from file
        try (
                var fs = new FileInputStream("data.bin");
                var os = new ObjectInputStream(fs);
        ) {
            ((List<GameGenre>) os.readObject())
                    .forEach(gameGenre -> {
                        System.out.printf("%s:\n", gameGenre.getName());
                        gameGenre.getGames().forEach(game -> System.out.printf("\t%s\n", game.toString()));
                    });
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nTask 7\n");
        var pool = new ForkJoinPool(4);
        pool.submit(() -> allGames.stream().toList().parallelStream().forEach(game -> {
            try {
                Thread.sleep(game.getMinimalPlayerAge() * 100L);
                System.out.println(game);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        })).join();
    }
}
