package io.github.doggymentor.exception.handler;

import io.github.doggymentor.domain.command.Command;
import io.github.doggymentor.exception.ApiError;
import io.github.doggymentor.exception.Error;
import io.github.doggymentor.exception.model.CommandException;
import io.github.doggymentor.exception.model.DogException;
import io.github.doggymentor.exception.model.ServiceException;
import io.github.doggymentor.exception.model.UserException;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(MongoException.class)
    public ResponseEntity<?> handleMongoException(final MongoException exception) {
        log.warn("Processing mongo exception: {}", exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handleServiceException(final ServiceException exception) {
        log.warn("Processing service exception: {}", exception.getMessage());

        Error error = exception.getError();
        return new ResponseEntity<>(new ApiError(error.getId(), error.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> handleUserException(final UserException exception) {
        log.warn("Processing user not found exception: {}", exception.getMessage());

        Error error = exception.getError();
        return new ResponseEntity<>(new ApiError(error.getId(), error.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DogException.class)
    public ResponseEntity<?> handleDogException(final DogException exception) {
        log.warn("Processing dog not found exception: {}", exception.getMessage());

        Error error = exception.getError();
        return new ResponseEntity<>(new ApiError(error.getId(), error.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommandException.class)
    public ResponseEntity<?> handleCommandException(final CommandException exception) {
        log.warn("Processing command not found exception: {}", exception.getMessage());

        Error error = exception.getError();
        return new ResponseEntity<>(new ApiError(error.getId(), error.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAbstractException(final Exception exception) {
        log.warn("Processing abstract exception: {}", exception.getMessage());

        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
