package com.comeon.ernesto.game.model.dto;

import lombok.*;

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