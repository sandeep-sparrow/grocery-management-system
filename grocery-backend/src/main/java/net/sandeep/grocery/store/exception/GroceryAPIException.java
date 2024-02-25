package net.sandeep.grocery.store.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Getter
@AllArgsConstructor
public class GroceryAPIException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = -7252605224212942274L;

    private HttpStatus status;
    private String message;
}
