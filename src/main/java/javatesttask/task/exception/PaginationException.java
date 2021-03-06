package javatesttask.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class PaginationException extends RuntimeException {

    public PaginationException(String message) {
        super(message);
    }
}
