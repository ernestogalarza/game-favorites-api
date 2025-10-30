package com.comeon.ernesto.game.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO representing a game .
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GameDto {

    private Long id;
    private String name;

}
