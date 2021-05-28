package javatesttask.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class MethodException extends RuntimeException {
    public MethodException(String message) {
        super(message);
    }
}
