package com.comeon.ernesto.game.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.comeon.ernesto.game.exception.ApiException;
import com.comeon.ernesto.game.exception.ErrorCode;
import com.comeon.ernesto.game.mapper.ServiceMapper;
import com.comeon.ernesto.game.model.dto.GameDto;
import com.comeon.ernesto.game.model.dto.GameFavoriteDto;
import com.comeon.ernesto.game.model.dto.GameFavoriteRequestDto;
import com.comeon.ernesto.game.model.dto.GameRequestDto;
import com.comeon.ernesto.game.model.entity.GameEntity;
import com.comeon.ernesto.game.model.entity.GameLoveEntity;
import com.comeon.ernesto.game.model.entity.PlayerEntity;
import com.comeon.ernesto.game.repository.GameLoveRepository;
import com.comeon.ernesto.game.repository.GameRepository;
import com.comeon.ernesto.game.repository.PlayerRepository;
import com.comeon.ernesto.game.service.GameService;

import jakarta.transaction.Transactional;


@Service
public class GameServiceImpl implements GameService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    GameLoveRepository gameLoveRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ServiceMapper serviceMapper;

    @Override
    public void like(GameFavoriteRequestDto request) {
        PlayerEntity player = playerRepository.findById(request.player())
                .orElseThrow(() -> new ApiException(ErrorCode.PLAYER_NOT_FOUND));
        GameEntity game = gameRepository.findById(request.game())
                .orElseThrow(() -> new ApiException(ErrorCode.GAME_NOT_FOUND));

        GameLoveEntity gameLove = GameLoveEntity.builder()
                .player(player)
                .game(game)
                .build();

        gameLoveRepository.save(gameLove);
    }

    @Override
    @Transactional
    public void dislike(GameFavoriteRequestDto request) {
        GameLoveEntity gameLove = gameLoveRepository
                .findByPlayerIdAndGameId(request.player(), request.game())
                .orElseThrow(() -> new ApiException(ErrorCode.GAMELOVE_NOT_FOUND));

        gameLoveRepository.delete(gameLove);
    }

    @Override
    public List<GameFavoriteDto> getFavoritesTop(int limit) {
        return  gameLoveRepository.findTopLovedGames(PageRequest.of(0, limit));

    }

    @Override
    public List<GameDto> getFavoritesPlayer(int idPlayer) {
        return  gameLoveRepository.findGamesLovedByPlayer(
                Long.valueOf(idPlayer));
    }

    @Override
    public void save(GameRequestDto request) {
        gameRepository.save(serviceMapper.toGameEntity(request));
    }

    @Override
    public List<GameDto> fetch(int page, int limit) {
        return gameRepository.findAll(PageRequest.of(page, limit)).getContent()
                .stream().map(serviceMapper::toGameDto).collect(Collectors.toList());
    }
}
