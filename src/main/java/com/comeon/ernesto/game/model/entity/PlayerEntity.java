package com.comeon.ernesto.game.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "player")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameLoveEntity> loves = new ArrayList<>();
}