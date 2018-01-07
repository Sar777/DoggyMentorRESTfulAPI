package io.github.doggymentor.domain.dog;

import io.github.doggymentor.domain.common.DbModel;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "dog_commands")
public class DogCommand extends DbModel implements Serializable {

    private static final long serialVersionUID = -6985702794075142835L;

    private String id;

    private String commandId;

    private CommandStatus status;

    public DogCommand() {
    }

    public DogCommand(String id, String commandId, CommandStatus status) {
        this.id = id;
        this.commandId = commandId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getCommandId() {
        return commandId;
    }

    public CommandStatus getStatus() {
        return status;
    }
}
