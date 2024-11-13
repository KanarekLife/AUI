package com.nieradko.games_api.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "game_genres")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameGenre {
    @Id
    @ToString.Exclude
    @Builder.Default
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Game> games = new ArrayList<>();
}
