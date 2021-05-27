package javatesttask.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalQueryParamException extends RuntimeException {

    public IllegalQueryParamException(String message) {
        super(message);
    }
}
