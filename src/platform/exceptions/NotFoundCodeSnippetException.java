package platform.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundCodeSnippetException extends RuntimeException {

    public NotFoundCodeSnippetException(Long id) {
        super("Entity with id " + id + " not found");
    }

}
