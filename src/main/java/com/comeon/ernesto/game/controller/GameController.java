package com.comeon.ernesto.game.controller;

import com.comeon.ernesto.game.api.GameApi;
import com.comeon.ernesto.game.mapper.ServiceMapper;
import com.comeon.ernesto.game.model.api.*;
import com.comeon.ernesto.game.service.GameService;
import com.comeon.ernesto.game.util.HelperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class GameController implements GameApi {

    @Autowired
    GameService gameService;

    @Autowired
    ServiceMapper serviceMapper;

    @Override
    public ResponseEntity<GeneralResponse> doGameDislikeDelete(GameFavoriteRequest gameFavoriteRequest) {
        log.info("START doGameDislikeDelete");
        gameService.dislike(serviceMapper.toGameFavoriteRequestDto(gameFavoriteRequest));
        return ResponseEntity.ok(HelperUtil.buildGeneralResponse());
    }

    @Override
    public ResponseEntity<List<GameResponse>> doGameFavoritesPlayerGet(Integer idplayer) {
        List<GameResponse> list =  gameService.getFavoritesPlayer(idplayer)
                .stream().map(serviceMapper::toGameResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<List<GameFavoriteResponse>> doGameFavoritesTopGet(Integer limit) {
        log.info("START doGameFavoritesTopGet");
        List<GameFavoriteResponse> list =  gameService.getFavoritesTop(limit)
                .stream().map(serviceMapper::toGameFavoriteResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @Override
    public ResponseEntity<List<GameResponse>> doGameFetchGet(Integer page, Integer limit) {
        log.info("START doGameFetchGet");
        List<GameResponse> list =  gameService.fetch(page,limit)
                .stream().map(serviceMapper::toGameResponse).collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }


    @Override
    public ResponseEntity<GeneralResponse> doGameLikePost(GameFavoriteRequest gameFavoriteRequest) {
        log.info("START doGameLikePost");
        gameService.like(serviceMapper.toGameFavoriteRequestDto(gameFavoriteRequest));
        return ResponseEntity.ok(HelperUtil.buildGeneralResponse());
    }

    @Override
    public ResponseEntity<GeneralResponse> doGameRegisterPost(GameRequest gameRequest) {
        log.info("START doGameRegisterPost");
        gameService.save(serviceMapper.toGameRequestDto(gameRequest));
        return ResponseEntity.ok(HelperUtil.buildGeneralResponse());
    }

}
