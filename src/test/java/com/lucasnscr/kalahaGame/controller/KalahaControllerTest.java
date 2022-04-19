package com.lucasnscr.kalahaGame.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasnscr.kalahaGame.model.Game;
import com.lucasnscr.kalahaGame.model.Player;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class KalahaControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createGame() throws Exception {
        MockHttpServletRequestBuilder initGameRequest = MockMvcRequestBuilders.post("/api/kalaha/games");

        mockMvc.perform(initGameRequest)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())

                .andExpect(MockMvcResultMatchers.jsonPath("$.gameStatus").value("INIT"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.size()", Matchers.is(14)))

                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.1.pitIndex").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.8.pitIndex").value(8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.14.pitIndex").value(14))

                .andExpect(MockMvcResultMatchers.jsonPath("$.player1.playerIndex").value(Player.PLAYER1_INDEX))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player2.playerIndex").value(Player.PLAYER2_INDEX))

                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.6.playerIndex").value(Player.PLAYER1_INDEX))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.13.playerIndex").value(Player.PLAYER2_INDEX))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.14.playerIndex").value(Player.PLAYER2_INDEX))

                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.5.stoneCount").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.12.stoneCount").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.14.stoneCount").value(0))

                .andReturn();
    }

    @Test
    public void playGame() throws Exception {

        final MockHttpServletRequestBuilder createGameRequest = MockMvcRequestBuilders.post("/api/kalaha/games");
        String responseString = mockMvc.perform(createGameRequest).andReturn().getResponse().getContentAsString();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Game game = objectMapper.readValue(responseString, Game.class);

        MockHttpServletRequestBuilder playGame = MockMvcRequestBuilders.put("/api/kalaha/games/"+game.getId()+"/pits/"+ 1);

        mockMvc.perform(playGame)
                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(game.getId()))

                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.size()", Matchers.is(14)))

                .andExpect(MockMvcResultMatchers.jsonPath("$.player1.playerIndex").value(Player.PLAYER1_INDEX))
                .andExpect(MockMvcResultMatchers.jsonPath("$.player2.playerIndex").value(Player.PLAYER2_INDEX))

                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.2.stoneCount").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.3.stoneCount").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.4.stoneCount").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.5.stoneCount").value(7))
                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.6.stoneCount").value(7))

                .andExpect(MockMvcResultMatchers.jsonPath("$.board.pits.7.stoneCount").value(1))

                .andExpect(MockMvcResultMatchers.jsonPath("$.gameStatus").value("PLAYER1_MOVIMENT"))

                .andReturn();
    }

    @Test
    public void findById() throws Exception {
        final MockHttpServletRequestBuilder createGameRequest = MockMvcRequestBuilders.post("/api/kalaha/games");
        String responseString = mockMvc.perform(createGameRequest).andReturn().getResponse().getContentAsString();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Game game = objectMapper.readValue(responseString, Game.class);

        MockHttpServletRequestBuilder findGame = MockMvcRequestBuilders.get("/api/kalaha/games/"+game.getId());
        mockMvc.perform(findGame)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
