package com.comeon.ernesto.game.mapper;
import com.comeon.ernesto.game.model.api.*;
import com.comeon.ernesto.game.model.dto.*;
import com.comeon.ernesto.game.model.entity.GameEntity;
import com.comeon.ernesto.game.model.entity.PlayerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMapper {

    PlayerRequestDto toPlayerRequestDto(PlayerRequest playerRequest);

    GameFavoriteRequestDto toGameFavoriteRequestDto (GameFavoriteRequest GameFavoriteRequest);

    GameRequestDto toGameRequestDto(GameRequest gameRequest);

    GameDto toGameDto(GameEntity gameEntity);

    GameFavoriteResponse toGameFavoriteResponse(GameFavoriteDto gameFavoriteDto);

    GameResponse toGameResponse(GameDto gameDto);

    default PlayerEntity toPlayer(PlayerRequestDto playerRequestDto){
        return PlayerEntity.builder()
                .username(playerRequestDto.username()).build();
    }

    default GameEntity toGameEntity(GameRequestDto gameRequestDto){
        return GameEntity.builder()
                .name(gameRequestDto.name()).build();
    }
}
