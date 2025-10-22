package com.comeon.ernesto.game.service;

import com.comeon.ernesto.game.model.dto.PlayerRequestDto;

public interface PlayerService {
    Long save(PlayerRequestDto playerRequestDto);

}
