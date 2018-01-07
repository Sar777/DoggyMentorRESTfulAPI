package io.github.doggymentor.domain.dog;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CommandStatus {

    NONE,
    IN_PROGRESS,
    COMPLETED;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
