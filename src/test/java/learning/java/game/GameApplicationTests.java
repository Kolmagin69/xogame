package learning.java.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import learning.java.game.model.Field;
import learning.java.game.model.Figure;
import learning.java.game.model.Game;
import learning.java.game.model.Point;
import learning.java.game.rest.GameRestController;
import learning.java.game.rest.PostBody;
import learning.java.game.rest.TurnBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameApplicationTests {

    @Autowired
    private GameRestController gameController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testPostGame() throws Exception {
        Figure inputFigure = Figure.O;
        PostBody postBody = new PostBody(){{
            setSide(inputFigure);
        }};

        MvcResult result = this.mockMvc.perform(post("/game")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postBody)))
                .andExpect(status().isOk())
                .andReturn();

        Game game = gameFromMvcResult(result);
        Figure actualFigure = game.getPlayers().get(0).getFigure();
        Figure unexpectedFigure = Figure.X;

        assertEquals(actualFigure, inputFigure);
        assertFalse(actualFigure == unexpectedFigure);
    }

    @Test
    void testFailIfPostRequestWithIncorrectBody() throws Exception {
        try {
            String requestBody = "{\"side\":\"P\"}";
            MvcResult result = postIncorrectBody(requestBody);
            Game game = gameFromMvcResult(result);
            fail();
        } catch (MismatchedInputException e) {
        }

        try {
            String requestBody = "{\"something\":\"O\"}";
            postIncorrectBody(requestBody);
            fail();
        } catch (Exception e) {

        }

        postIncorrectBody("");
    }

    @Test
    void testGameFromId() throws Exception {
        Game expectedGame = postAndReturnGame();
        String urlTemplate = "/game/" + expectedGame.getId();
        MvcResult result = this.mockMvc.perform(get(urlTemplate))
                .andExpect(status().isOk())
                .andReturn();

        Game actualGame = gameFromMvcResult(result);
        assertEquals(actualGame.getId(), expectedGame.getId());

    }

    @Test
    void testFailTryGetGameWithIncorrectId() throws Exception {
        Game game = postAndReturnGame();

        String urlTemplateIncorrect = "/game/" + UUID.randomUUID();
        String urlTemplateCorrect = "/game/" + game.getId();
        MvcResult result1 = this.mockMvc.perform(get(urlTemplateCorrect))
                .andExpect(status().isOk())
                .andReturn();
        Game game1 = gameFromMvcResult(result1);
        assertEquals(game.getId(), game1.getId());

        this.mockMvc.perform(get(urlTemplateIncorrect))
                .andExpect(content().bytes(new byte[0]));
    }

    @Test
    void testTurn() throws Exception {
        Game game  = postAndReturnGame();
        TurnBody turnBody = new TurnBody(2) {{
            setX(0);
            setY(0);
        }};

        String urlTemplate = "/game/" + game.getId() + "/turn";
        MvcResult result = this.mockMvc.perform(post(urlTemplate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(turnBody)))
                .andExpect(status().isOk())
                .andReturn();

        Game actualGame = gameFromMvcResult(result);
        Field expectedField = new Field(3){{
            setFigures(new Point(0, 0), Figure.X);
        }};
        Figure[][] actualFigures = actualGame.getField().getFigures();
        Figure[][] expectedFigures = expectedField.getFigures();

        assertEquals(actualFigures[0][0], expectedFigures[0][0]);
        assertFalse(Arrays.equals(actualFigures, expectedFigures));
    }

    @Test
    void testFailWhenTryTurnWIthIncorrectGameId() throws Exception {
        postAndReturnGame();
        TurnBody turnBody = new TurnBody(2) {{
            setX(1);
            setY(2);
        }};

        String urlTemplate = "/game/" + UUID.randomUUID() + "/turn";
        try {
            this.mockMvc.perform(post(urlTemplate)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(turnBody)));
            fail();
        } catch (Exception e) {

        }
    }


    private Game gameFromMvcResult(MvcResult result) throws Exception {
        return objectMapper.readValue(result.getResponse().getContentAsString(), Game.class);
    }

    private Game postAndReturnGame() throws Exception {
        String requestBody1 = "{\"side\":\"X\"}";
        MvcResult result = this.mockMvc.perform(post("/game")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody1))
                .andExpect(status().isOk())
                .andReturn();

        return gameFromMvcResult(result);
    }

    private  MvcResult postIncorrectBody(String requestBody) throws Exception {
        MvcResult result = this.mockMvc.perform(post("/game")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andReturn();

        return result;
    }

}
