package io.github.doggymentor.exception.model;

import io.github.doggymentor.exception.AbstractError;
import io.github.doggymentor.exception.Error;

public class ServiceException extends AbstractError {

    private static final long serialVersionUID = -8658131859261427602L;

    public ServiceException(Error error) {
        super(error);
    }
}
