package javatesttask.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoUnitFoundException extends RuntimeException {

    public NoUnitFoundException(String message) {
        super(message);
    }
}
