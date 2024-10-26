package com.nieradko.lab03.components;

import com.nieradko.lab03.entities.Game;
import com.nieradko.lab03.entities.GameGenre;
import com.nieradko.lab03.services.GameGenresService;
import com.nieradko.lab03.services.GamesService;
import org.springframework.boot.CommandLineRunner;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminConsoleComponent implements CommandLineRunner {
    private final GamesService _gamesService;
    private final GameGenresService _gameGenresService;
    public AdminConsoleComponent(GamesService gamesService, GameGenresService gameGenresService) {
        _gamesService = gamesService;
        _gameGenresService = gameGenresService;
    }

    @Override
    public void run(String... args) throws Exception {
        var scanner = new Scanner(System.in);
        var isRunning = true;
        while (isRunning) {
            var command = scanner.nextLine();
            switch (command) {
                case "get:genres" -> {
                    _gameGenresService.findAll()
                            .stream()
                            .sorted(Comparator.comparing(GameGenre::getName))
                            .forEach(genre -> System.out.printf("\t%s\n", genre));
                }
                case "get:games" -> {
                    _gamesService.findAll()
                            .stream()
                            .sorted(Comparator.comparing(Game::getName))
                            .forEach(game -> System.out.printf("\t%s\n", game));
                }
                case "get:games:genre" -> {
                    System.out.println("Enter genre:");
                    var genre = _gameGenresService.findByName(scanner.nextLine().trim()).stream().findFirst();

                    if (genre.isEmpty()) {
                        System.out.println("Genre not found");
                        return;
                    }

                    _gamesService.findByGenre(genre.get())
                            .stream()
                            .sorted(Comparator.comparing(Game::getName))
                            .forEach(game -> System.out.printf("\t%s\n", game));
                }
                case "add:genre" -> {
                    System.out.println("Enter genre name:");
                    var name = scanner.nextLine().trim();
                    System.out.println("Does it support multiplayer? [y/N]");
                    var doesSupportMultiplayer = scanner.nextLine().trim().equalsIgnoreCase("y");
                    _gameGenresService.create(GameGenre.builder()
                            .name(name)
                            .supportsMultiplayer(doesSupportMultiplayer)
                            .build());
                    System.out.printf("Added genre %s\n", name);
                }
                case "del:genre" -> {
                    System.out.println("Enter genre name:");
                    var name = scanner.nextLine().trim();
                    var genre = _gameGenresService.findByName(name).stream().findFirst();

                    if (genre.isEmpty()) {
                        System.out.println("Genre not found");
                        return;
                    }

                    _gameGenresService.delete(genre.get());
                    System.out.printf("Deleted genre %s\n", name);
                }
                case "add:game" -> {
                    System.out.println("Enter name:");
                    var name = scanner.nextLine().trim();
                    System.out.println("What's minimal player age?");
                    var minimalPlayerAge = scanner.nextLine().trim();
                    System.out.println("What's game genre?");
                    var genreName = scanner.nextLine().trim();
                    System.out.println("Enter genre name:");

                    int minPlayerAge;
                    try {
                        minPlayerAge = Integer.parseInt(minimalPlayerAge);
                    } catch (InputMismatchException e) {
                        System.out.println("Expected integer values for minimalPlayerAge");
                        return;
                    }

                    var genre = _gameGenresService.findByName(genreName).stream().findFirst();
                    if (genre.isEmpty()) {
                        System.out.println("Genre not found");
                        return;
                    }

                    var game = Game.builder()
                            .name(name)
                            .minimalPlayerAge(minPlayerAge)
                            .genre(genre.get())
                            .build();
                    _gamesService.create(game);
                    System.out.printf("Created game %s\n", name);
                }
                case "del:game" -> {
                    System.out.println("Enter name:");
                    var name = scanner.nextLine().trim();

                    var game = _gamesService.findByName(name).stream().findFirst();
                    if (game.isEmpty()) {
                        System.out.println("Game not found");
                        return;
                    }
                    _gamesService.delete(game.get());
                    System.out.printf("Deleted game %s\n", name);
                }
                case "help" -> {
                    System.out.println("Available commands (with explanation):");
                    System.out.println("get:genres - get all genres");
                    System.out.println("get:games - get all games");
                    System.out.println("get:games:genre - get all games by genre");
                    System.out.println("add:genre - add genre");
                    System.out.println("del:genre - delete genre");
                    System.out.println("add:game - add game");
                    System.out.println("del:game - delete game");
                    System.out.println("exit - exit the program");
                }
                case "exit" -> {
                    isRunning = false;
                }
                default -> {
                    System.out.println("Invalid command");
                }
            }
        }
    }
}
