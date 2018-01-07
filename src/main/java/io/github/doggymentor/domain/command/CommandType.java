package io.github.doggymentor.domain.command;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CommandType {

    COMMAND,
    TRUCK;

    @JsonValue
    public int toValue() {
        return ordinal();
    }
}
