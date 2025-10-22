package com.comeon.ernesto.game.service.impl;

import com.comeon.ernesto.game.exception.ApiException;
import com.comeon.ernesto.game.exception.ErrorCode;
import com.comeon.ernesto.game.model.dto.GameFavoriteRequestDto;
import com.comeon.ernesto.game.model.entity.GameEntity;
import com.comeon.ernesto.game.model.entity.GameLoveEntity;
import com.comeon.ernesto.game.model.entity.PlayerEntity;
import com.comeon.ernesto.game.repository.GameLoveRepository;
import com.comeon.ernesto.game.repository.GameRepository;
import com.comeon.ernesto.game.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private GameLoveRepository gameLoveRepository;

    @InjectMocks
    private GameServiceImpl gameService;

    private PlayerEntity player;
    private GameEntity game;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        player = PlayerEntity.builder().id(1L).username("ernesto").build();
        game = GameEntity.builder().id(2L).name("Zelda").build();
    }


    @Test
    void shouldLikeGameSuccessfully() {
        GameFavoriteRequestDto request = new GameFavoriteRequestDto(1L, 2L);
        when(playerRepository.findById(anyLong())).thenReturn(Optional.of(player));
        when(gameRepository.findById(anyLong())).thenReturn(Optional.of(game));

        gameService.like(request);
        verify(gameLoveRepository, times(1))
                .save(any(GameLoveEntity.class));
    }

    @Test
    void shouldThrowWhenPlayerNotFound() {
        GameFavoriteRequestDto request = new GameFavoriteRequestDto(99L, 2L);
        when(playerRepository.findById(2L)).thenReturn(Optional.empty());


        assertThatThrownBy(() -> gameService.like(request))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining(ErrorCode.PLAYER_NOT_FOUND.getDescription());
    }

    @Test
    void shouldThrowWhenGameNotFound() {

        GameFavoriteRequestDto request = new GameFavoriteRequestDto(1L, 88L);
        when(playerRepository.findById(anyLong())).thenReturn(Optional.of(player));
        when(gameRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> gameService.like(request))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining(ErrorCode.GAME_NOT_FOUND.getDescription());
    }


    @Test
    void shouldDislikeGameSuccessfully() {
        GameFavoriteRequestDto request = new GameFavoriteRequestDto(1L, 2L);
        GameLoveEntity love = GameLoveEntity.builder().id(10L).build();

        when(gameLoveRepository.findByPlayerIdAndGameId(anyLong(), anyLong()))
                .thenReturn(Optional.of(love));

        gameService.dislike(request);

        verify(gameLoveRepository, times(1)).delete(love);
    }


    @Test
    void shouldThrowWhenGameLoveNotFound() {

        GameFavoriteRequestDto request = new GameFavoriteRequestDto(1L, 2L);

        when(gameLoveRepository.findByPlayerIdAndGameId(anyLong(), anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> gameService.dislike(request))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining(ErrorCode.GAMELOVE_NOT_FOUND.getDescription());
    }

}