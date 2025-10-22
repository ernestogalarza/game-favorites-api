package com.comeon.ernesto.game.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApiError {
    private String code;
    private String message;
    private int status;
}
