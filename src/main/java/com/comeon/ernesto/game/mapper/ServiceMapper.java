package com.comeon.ernesto.game.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.comeon.ernesto.game.model.api.GameFavoriteRequest;
import com.comeon.ernesto.game.model.api.GameFavoriteResponse;
import com.comeon.ernesto.game.model.api.GameRequest;
import com.comeon.ernesto.game.model.api.GameResponse;
import com.comeon.ernesto.game.model.api.PlayerRequest;
import com.comeon.ernesto.game.model.dto.GameDto;
import com.comeon.ernesto.game.model.dto.GameFavoriteDto;
import com.comeon.ernesto.game.model.dto.GameFavoriteRequestDto;
import com.comeon.ernesto.game.model.dto.GameRequestDto;
import com.comeon.ernesto.game.model.dto.PlayerRequestDto;
import com.comeon.ernesto.game.model.entity.GameEntity;
import com.comeon.ernesto.game.model.entity.PlayerEntity;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMapper {

    PlayerRequestDto toPlayerRequestDto(PlayerRequest playerRequest);

    GameFavoriteRequestDto toGameFavoriteRequestDto (GameFavoriteRequest gameFavoriteRequest);

    GameRequestDto toGameRequestDto(GameRequest gameRequest);

    GameDto toGameDto(GameEntity gameEntity);

    GameFavoriteResponse toGameFavoriteResponse(GameFavoriteDto gameFavoriteDto);

    GameResponse toGameResponse(GameDto gameDto);

    PlayerEntity toPlayer(PlayerRequestDto playerRequestDto);

    default GameEntity toGameEntity(GameRequestDto gameRequestDto) {
        return GameEntity.builder()
                .name(gameRequestDto.name()).build();
    }
}
