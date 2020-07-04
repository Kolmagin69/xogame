package learning.java.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(RuntimeException ex){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(
                ex.getMessage(),
                ex.getClass().toString(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("UTC+3")));

        return new ResponseEntity<>(apiExceptionMessage, badRequest);
    }

    @ExceptionHandler(value = {NotFoundExceptions.class})
    public ResponseEntity<Object> handleNotFoundExceptions(RuntimeException ex){

        HttpStatus badRequest = HttpStatus.NOT_FOUND;

        ApiExceptionMessage apiExceptionMessage = new ApiExceptionMessage(
                ex.getMessage(),
                ex.getClass().toString(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("UTC+3")));

        return new ResponseEntity<>(apiExceptionMessage, badRequest);
    }
}
