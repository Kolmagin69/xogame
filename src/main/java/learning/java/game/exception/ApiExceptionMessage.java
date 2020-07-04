package learning.java.game.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiExceptionMessage {

    private final String message;

    private final String exception;

    private final HttpStatus httpStatus;

    private final ZonedDateTime dateTime;

    public ApiExceptionMessage(String message,
                               String exception, HttpStatus httpStatus, ZonedDateTime dateTime) {

        this.message = message;
        this.exception = exception;
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
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

    public String getException() {
        return exception;
    }
}
