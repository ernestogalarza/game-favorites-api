package com.comeon.ernesto.game.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    PLAYER_NOT_FOUND("PLAYER_NOT_FOUND", "Player not found", HttpStatus.NOT_FOUND),
    GAME_NOT_FOUND("GAME_NOT_FOUND", "Game not found", HttpStatus.NOT_FOUND),
    GAMELOVE_NOT_FOUND("GAMELOVE_NOT_FOUND", "No favorite game has found", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR("INTERNAL_ERROR", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String description;
    private final HttpStatus httpStatus;


}
