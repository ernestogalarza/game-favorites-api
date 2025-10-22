package com.comeon.ernesto.game.service;

import com.comeon.ernesto.game.model.dto.GameDto;
import com.comeon.ernesto.game.model.dto.GameFavoriteDto;
import com.comeon.ernesto.game.model.dto.GameFavoriteRequestDto;
import com.comeon.ernesto.game.model.dto.GameRequestDto;

import java.util.List;

public interface GameService {
    void like(GameFavoriteRequestDto request);
    void dislike(GameFavoriteRequestDto request);
    List<GameFavoriteDto> getFavoritesTop(int limit);
    List<GameDto> getFavoritesPlayer(int idPlayer);
    void save(GameRequestDto request);
    List<GameDto> fetch(int page, int limit);
}
