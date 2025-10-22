package com.comeon.ernesto.game.controller;

import com.comeon.ernesto.game.mapper.ServiceMapper;
import com.comeon.ernesto.game.model.api.PlayerRequest;
import com.comeon.ernesto.game.service.PlayerService;
import com.comeon.ernesto.game.util.HelperUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class PlayerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PlayerService playerService;

    @Mock
    private ServiceMapper serviceMapper;


    @InjectMocks
    private PlayerController playerController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }

    @Test
    void shouldRegisterPlayerSuccessfully() throws Exception {

        PlayerRequest request = new PlayerRequest();
        request.setUsername("contreras");

        when(playerService.save(serviceMapper.toPlayerRequestDto(request))).thenReturn(1L);


        mockMvc.perform(post("/player")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(HelperUtil.getObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}