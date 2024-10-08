package com.nieradko;

import com.nieradko.dtos.CharacterDto;
import com.nieradko.entities.Character;
import com.nieradko.entities.Profession;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 2\n");
        var professionsByName = Stream.of(
                Profession.builder()
                        .name("Spellcaster")
                        .baseArmor(10)
                        .build(),
                Profession.builder()
                        .name("Orc")
                        .baseArmor(250)
                        .build(),
                Profession.builder()
                        .name("Swordmaster")
                        .baseArmor(125)
                        .build()
        ).collect(Collectors.toMap(Profession::getName, x -> x));
        var allProfessions = professionsByName.values().stream().toList();

        var characters = List.of(
                Character.builder()
                        .name("John")
                        .level(5)
                        .profession(professionsByName.get("Swordmaster"))
                        .build(),
                Character.builder()
                        .name("Jack")
                        .level(15)
                        .profession(professionsByName.get("Orc"))
                        .build(),
                Character.builder()
                        .name("Johnson")
                        .level(20)
                        .profession(professionsByName.get("Orc"))
                        .build(),
                Character.builder()
                        .name("Pikachu")
                        .level(25)
                        .profession(professionsByName.get("Spellcaster"))
                        .build()
        );

        allProfessions.forEach(profession -> {
            System.out.printf("%s:\n", profession.getName());
            profession.getCharacters().forEach(character -> System.out.printf("\t%s\n", character.toString()));
        });

        System.out.println("Task 3\n");
        var allCharacters = allProfessions.stream()
                .flatMap(x -> x.getCharacters().stream())
                .collect(Collectors.toSet());
        allCharacters.forEach(System.out::println);

        System.out.println("Task 4\n");
        allCharacters.stream()
                .filter(x -> x.getLevel() > 15)
                .sorted(Comparator.comparing(x -> x.getProfession().getName()))
                .forEach(System.out::println);

        System.out.println("Task 5\n");
        var allCharacterDtos = allCharacters.stream()
                .map(x -> CharacterDto.builder()
                        .name(x.getName())
                        .level(x.getLevel())
                        .profession(x.getProfession().getName())
                        .build()
                )
                .sorted()
                .toList();
        allCharacterDtos.forEach(System.out::println);

        System.out.println("Task 6\n");
        // Save to file
        try (
                var fs = new FileOutputStream("professions.bin");
                var os = new ObjectOutputStream(fs);
        ) {
            os.writeObject(allProfessions);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Read from file
        try (
                var fs = new FileInputStream("professions.bin");
                var os = new ObjectInputStream(fs);
        ) {
            ((List<Profession>) os.readObject())
                    .forEach(profession -> {
                        System.out.printf("%s:\n", profession.getName());
                        profession.getCharacters().forEach(character -> System.out.printf("\t%s\n", character.toString()));
                    });
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Task 7\n");
        var pool = new ForkJoinPool(4);
        pool.submit(() -> allCharacters.stream().toList().parallelStream().forEach(character -> {
            try {
                Thread.sleep(character.getLevel() * 50L);
                System.out.println(character);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        })).join();
    }
}
