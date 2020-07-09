package learning.java.game.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiErrorMessage {

    private final String generalMessage;

    private final String message;

    private final HttpStatus httpStatus;

    private final ZonedDateTime dateTime;

    public ApiErrorMessage(String generalMessage,
                           String message,
                           HttpStatus httpStatus,
                           ZonedDateTime dateTime) {

        this.generalMessage = generalMessage;
        this.message = message;
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
    }

    public String getGeneralMessage() {
        return generalMessage;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }
}
