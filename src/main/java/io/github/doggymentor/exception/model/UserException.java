package io.github.doggymentor.exception.model;

import io.github.doggymentor.exception.AbstractError;
import io.github.doggymentor.exception.Error;

public class UserException extends AbstractError {

    public UserException(final Error error) {
        super(error);
    }
}
