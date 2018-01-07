package io.github.doggymentor.exception.model;

import io.github.doggymentor.exception.AbstractError;
import io.github.doggymentor.exception.Error;

public class CommandException extends AbstractError {

    public CommandException(final Error error) {
        super(error);
    }
}
