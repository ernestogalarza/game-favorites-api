package com.comeon.ernesto.game.model.entity;

import jakarta.persistence.*;
import lombok.Builder;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "game")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameLoveEntity> loves = new ArrayList<>();

}