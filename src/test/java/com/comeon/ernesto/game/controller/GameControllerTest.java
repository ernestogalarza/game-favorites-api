package com.comeon.ernesto.game.controller;

import com.comeon.ernesto.game.mapper.ServiceMapper;
import com.comeon.ernesto.game.model.api.GameFavoriteRequest;
import com.comeon.ernesto.game.model.api.GameFavoriteResponse;
import com.comeon.ernesto.game.model.api.GameRequest;
import com.comeon.ernesto.game.model.dto.GameFavoriteDto;
import com.comeon.ernesto.game.model.dto.GameFavoriteRequestDto;
import com.comeon.ernesto.game.model.dto.GameRequestDto;
import com.comeon.ernesto.game.service.GameService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.comeon.ernesto.game.util.HelperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class GameControllerTest {
    private MockMvc mockMvc;

    @Mock
    private GameService gameService;

    @Mock
    private ServiceMapper serviceMapper;

    @InjectMocks
    private GameController gameController;

    private GameFavoriteRequest gameFavoriteRequest;
    private GameRequest gameRequest;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();

        gameFavoriteRequest = new GameFavoriteRequest();
        gameFavoriteRequest.setGame(1);
        gameFavoriteRequest.setPlayer(2);

        gameRequest = new GameRequest();
        gameRequest.setUsername("mario");
    }

    @Test
    void doGameDislikePost_shouldReturnOk() throws Exception {
        when(serviceMapper.toGameFavoriteRequestDto(gameFavoriteRequest))
                .thenReturn(new GameFavoriteRequestDto(2L, 1L));

        mockMvc.perform(post("/game/dislike")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(HelperUtil.getObjectMapper().writeValueAsString(gameFavoriteRequest)))
                .andExpect(status().isOk());

        verify(gameService, times(1)).dislike(any());
    }

    @Test
    void doGameLikePost_shouldReturnOk() throws Exception {
        GameFavoriteRequestDto dto = new GameFavoriteRequestDto(2L, 1L);
        when(serviceMapper.toGameFavoriteRequestDto(gameFavoriteRequest))
                .thenReturn(dto);

        mockMvc.perform(post("/game/like")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(HelperUtil.getObjectMapper().writeValueAsString(gameFavoriteRequest)))
                .andExpect(status().isOk());

        verify(gameService, times(1)).like(dto);
    }

    @Test
    void doGameRegisterPost_shouldReturnOk() throws Exception {

        GameRequestDto dto= new GameRequestDto("mario");

        when(serviceMapper.toGameRequestDto(gameRequest))
                .thenReturn(dto);

        mockMvc.perform(post("/game")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(HelperUtil.getObjectMapper().writeValueAsString(gameRequest)))
                .andExpect(status().isOk());

        verify(gameService, times(1)).save(dto);
    }


    @Test
    void doGameFavoritesGet_shouldReturnList() throws Exception {
        GameFavoriteDto dto =new GameFavoriteDto(1L, "Game1", 10L);

        when(gameService.getFavoritesTop(5)).thenReturn(List.of(dto));
        when(serviceMapper.toGameFavoriteResponse(dto)).thenReturn(new GameFavoriteResponse().id(1).name("zelda").totalLikes(10));

        mockMvc.perform(get("/game/favorites/top")
                        .param("limit", "5"))
                .andExpect(status().isOk());

        verify(gameService, times(1)).getFavoritesTop(5);
    }
}