package com.comeon.ernesto.game.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO representing a favorite game of a player.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class GameFavoriteDto {
    private Long id;
    private String name;
    private Long totalLikes;
}
