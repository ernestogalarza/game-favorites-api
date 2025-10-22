package com.comeon.ernesto.game.model.dto;

public record GameFavoriteRequestDto (
        Long game,
        Long player
) {}