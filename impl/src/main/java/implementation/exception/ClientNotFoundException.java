package implementation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String id) {
        super("No such client " + id);
    }
}
