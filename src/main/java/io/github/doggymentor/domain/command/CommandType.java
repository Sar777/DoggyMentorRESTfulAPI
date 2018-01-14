package io.github.doggymentor.domain.command;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {

    COMMAND,
    TRUCK;

    private static Map<Integer, CommandType> map = new HashMap<>();

    static {
        for (CommandType legEnum : CommandType.values()) {
            map.put(legEnum.toValue(), legEnum);
        }
    }

    @JsonValue
    public int toValue() {
        return ordinal();
    }

    public static CommandType valueOf(int id) {
        return map.get(id);
    }
}
