package learning.java.game.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GameExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(GameExceptionHandler.class);
    @ExceptionHandler(value = {GameOverException.class})
    public ResponseEntity<Object> handleGameOverExceptions(Exception ex){

        HttpStatus badRequest = HttpStatus.NOT_FOUND;

        GameOverException gameEx = (GameOverException) ex;
        GameOverMessage message = new GameOverMessage(gameEx.getGame());

//        logger.info(ex.getMessage());
        return new ResponseEntity<>(message, badRequest);
    }

}
