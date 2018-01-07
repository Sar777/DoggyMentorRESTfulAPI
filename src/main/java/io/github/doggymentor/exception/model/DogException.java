package io.github.doggymentor.exception.model;

import io.github.doggymentor.exception.AbstractError;
import io.github.doggymentor.exception.Error;

public class DogException extends AbstractError {

    private static final long serialVersionUID = 2967357473314163159L;

    public DogException(final Error error) {
        super(error);
    }
}
