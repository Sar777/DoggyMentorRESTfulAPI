package io.github.doggymentor.exception;

public abstract class AbstractError extends RuntimeException {

    private final Error error;

    public AbstractError(final Error error) {
        super(error.getMessage());
        this.error = error;
    }

    public Error getError() {
        return error;
    }
}
