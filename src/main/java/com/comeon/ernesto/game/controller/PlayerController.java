package com.comeon.ernesto.game.controller;

import com.comeon.ernesto.game.api.PlayerApi;
import com.comeon.ernesto.game.mapper.ServiceMapper;
import com.comeon.ernesto.game.model.api.GeneralResponse;
import com.comeon.ernesto.game.model.api.PlayerRequest;
import com.comeon.ernesto.game.service.PlayerService;
import com.comeon.ernesto.game.util.HelperUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class PlayerController implements PlayerApi {
    @Autowired
    PlayerService playerService;

    @Autowired
    ServiceMapper serviceMapper;

    @Override
    public ResponseEntity<GeneralResponse> doPlayerRegisterPost(PlayerRequest playerRequest) {
        Long result =  playerService.save(serviceMapper.toPlayerRequestDto(playerRequest));
        return ResponseEntity.ok(HelperUtil.buildGeneralResponse());
    }
}
