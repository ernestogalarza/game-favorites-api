package com.comeon.ernesto.game.service.impl;

import com.comeon.ernesto.game.mapper.ServiceMapper;
import com.comeon.ernesto.game.model.dto.PlayerRequestDto;
import com.comeon.ernesto.game.model.entity.PlayerEntity;
import com.comeon.ernesto.game.repository.PlayerRepository;
import com.comeon.ernesto.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    ServiceMapper serviceMapper;

    public Long save(PlayerRequestDto playerRequestDto) {

        PlayerEntity playerEntity = playerRepository.save(
                 serviceMapper.toPlayer(playerRequestDto)
        );

        return playerEntity.getId();

    }
}
