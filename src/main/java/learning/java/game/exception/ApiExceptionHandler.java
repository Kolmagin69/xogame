package learning.java.game.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    private final String generalMessage = "Sorry. Some kind of error occurred. " +
            "Read the message, maybe it will help";

    @ExceptionHandler(value = {IllegalArgumentException.class, IndexOutOfBoundsException.class,
            IncorrectBodyException.class})
    public ResponseEntity<Object> handleIllegalArgumentAndIndexOutOfBoundsException(Exception ex){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                generalMessage, ex.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("UTC+3")));

        return new ResponseEntity<>(apiErrorMessage, badRequest);
    }

    @ExceptionHandler(value = {InvalidFormatException.class})
    public ResponseEntity<Object> handleInvalidFormatException(Exception ex){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                generalMessage,
                "Sent request with incorrect body.",
                badRequest,
                ZonedDateTime.now(ZoneId.of("UTC+3")));

        return new ResponseEntity<>(apiErrorMessage, badRequest);
    }

    @ExceptionHandler(value = {NotFoundExceptions.class})
    public ResponseEntity<Object> handleNotFoundExceptions(Exception ex){

        HttpStatus badRequest = HttpStatus.NOT_FOUND;

        ApiErrorMessage apiErrorMessage = new ApiErrorMessage(
                generalMessage, ex.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("UTC+3")));

        return new ResponseEntity<>(apiErrorMessage, badRequest);
    }
}
