package com.comeon.ernesto.game.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_love")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameLoveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;
}